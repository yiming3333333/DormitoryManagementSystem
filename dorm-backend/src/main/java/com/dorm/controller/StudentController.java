package com.dorm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dorm.common.R;
import com.dorm.entity.CheckIn;
import com.dorm.entity.Dormitory;
import com.dorm.entity.Student;
import com.dorm.exception.BusinessException;
import com.dorm.service.CheckInService;
import com.dorm.service.DormitoryService;
import com.dorm.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // 新增学生
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody @Valid Student student) {
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            return R.error("密码不能为空");
        }
        student.setPassword(encoder.encode(student.getPassword()));
        return R.ok(studentService.save(student));
    }

    // 删除学生
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        // 检查是否在住
        long count = checkInService.lambdaQuery()
                .eq(CheckIn::getStudentId, id)
                .eq(CheckIn::getStatus, 0)
                .count();

        if (count > 0) {
            return R.error("该学生存在在住记录，请先办理退宿");
        }
        return R.ok(studentService.removeById(id));
    }

    // 修改学生
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody @Valid Student student) {
        if (student.getPassword() != null && !student.getPassword().isEmpty()) {
            student.setPassword(encoder.encode(student.getPassword()));
        } else {
            student.setPassword(null);
        }
        return R.ok(studentService.updateById(student));
    }

    // 分页列表
    @GetMapping("/list")
    public R<Page<Student>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer status) {

        Page<Student> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(name != null && !name.isEmpty(), Student::getName, name)
                .eq(status != null, Student::getStatus, status)
                .orderByDesc(Student::getStudentId);

        studentService.page(page, wrapper);

        // 密码置空
        page.getRecords().forEach(s -> s.setPassword(null));
        return R.ok(page);
    }

    // 详情
    @GetMapping("/detail/{id}")
    public R<Student> getById(@PathVariable Integer id) {
        Student student = studentService.getById(id);
        if (student != null) {
            student.setPassword(null);
        }
        return R.ok(student);
    }

    // 入住
    @PostMapping("/checkIn")
    @Transactional
    public R<Boolean> checkIn(@RequestBody @Valid CheckIn checkIn) {
        Student student = studentService.getById(checkIn.getStudentId());
        if (student == null) {
            throw new BusinessException("学生不存在");
        }
        if (student.getStatus() != null && student.getStatus() == 1) {
            throw new BusinessException("该学生已入住");
        }

        Dormitory dormitory = dormitoryService.getById(checkIn.getDormId());
        if (dormitory == null) {
            throw new BusinessException("宿舍不存在");
        }
        if (dormitory.getStatus() != null && dormitory.getStatus() == 2) {
            throw new BusinessException("该宿舍维修中，无法入住");
        }
        if (dormitory.getCurrentCount() >= dormitory.getCapacity()) {
            throw new BusinessException("该宿舍已满员");
        }

        // 性别校验
        if (dormitory.getCurrentCount() > 0) {
            CheckIn existing = checkInService.lambdaQuery()
                    .eq(CheckIn::getDormId, checkIn.getDormId())
                    .eq(CheckIn::getStatus, 0)
                    .last("LIMIT 1")
                    .one();

            if (existing != null) {
                Student existingStudent = studentService.getById(existing.getStudentId());
                if (existingStudent != null && !existingStudent.getGender().equals(student.getGender())) {
                    String genderName = existingStudent.getGender() == 0 ? "女" : "男";
                    throw new BusinessException("该宿舍已有" + genderName + "生入住，性别不符");
                }
            }
        }

        // 保存入住
        checkIn.setCheckInDate(LocalDate.now());
        checkIn.setStatus(0);
        checkInService.save(checkIn);

        // 更新学生状态
        student.setStatus(1);
        studentService.updateById(student);

        // 更新宿舍人数
        dormitory.setCurrentCount(dormitory.getCurrentCount() + 1);
        if (dormitory.getCurrentCount() >= dormitory.getCapacity()) {
            dormitory.setStatus(1);
        }
        dormitoryService.updateById(dormitory);

        return R.ok(true);
    }

    // 退宿
    @PostMapping("/checkOut/{recordId}")
    @Transactional
    public R<Boolean> checkOut(@PathVariable Integer recordId) {
        CheckIn checkIn = checkInService.getById(recordId);
        if (checkIn == null) {
            throw new BusinessException("入住记录不存在");
        }
        if (checkIn.getStatus() != null && checkIn.getStatus() == 1) {
            throw new BusinessException("该记录已退宿");
        }

        // 更新退宿记录
        checkIn.setCheckOutDate(LocalDate.now());
        checkIn.setStatus(1);
        checkInService.updateById(checkIn);

        // 更新学生状态
        Student student = new Student();
        student.setStudentId(checkIn.getStudentId());
        student.setStatus(2);
        studentService.updateById(student);

        // 更新宿舍
        Dormitory dormitory = dormitoryService.getById(checkIn.getDormId());
        if (dormitory != null) {
            int newCount = Math.max(dormitory.getCurrentCount() - 1, 0);
            dormitory.setCurrentCount(newCount);

            if (dormitory.getStatus() != null && dormitory.getStatus() != 2) {
                dormitory.setStatus(newCount >= dormitory.getCapacity() ? 1 : 0);
            }
            dormitoryService.updateById(dormitory);
        }

        return R.ok(true);
    }

    // 入住列表
    @GetMapping("/checkInList")
    public R<List<CheckIn>> checkInList() {
        return R.ok(checkInService.list());
    }
}