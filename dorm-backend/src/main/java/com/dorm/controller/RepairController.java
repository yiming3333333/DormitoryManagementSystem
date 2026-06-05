package com.dorm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorm.common.R;
import com.dorm.entity.CheckIn;
import com.dorm.entity.Dormitory;
import com.dorm.entity.Repair;
import com.dorm.entity.Student;
import com.dorm.exception.BusinessException;
import com.dorm.service.CheckInService;
import com.dorm.service.DormitoryService;
import com.dorm.service.RepairService;
import com.dorm.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private CheckInService checkInService;

    // 新增报修
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody @Valid Repair repair) {
        // 1. 判断学生是否存在
        Student student = studentService.getById(repair.getStudentId());
        if (student == null) {
            throw new BusinessException("学生不存在");
        }

        // 2. 判断宿舍是否存在
        Dormitory dormitory = dormitoryService.getById(repair.getDormId());
        if (dormitory == null) {
            throw new BusinessException("宿舍不存在");
        }

        // 3. 判断学生是否入住该宿舍
        long count = checkInService.count(
                new LambdaQueryWrapper<CheckIn>()
                        .eq(CheckIn::getStudentId, repair.getStudentId())
                        .eq(CheckIn::getDormId, repair.getDormId())
                        .eq(CheckIn::getStatus, 0)
        );

        if (count == 0) {
            throw new BusinessException("该学生未入住此宿舍，无法报修");
        }

        // 4. 保存报修
        repair.setStatus(0);
        repair.setCreateTime(LocalDateTime.now());
        return R.ok(repairService.save(repair));
    }

    // 删除报修
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return R.ok(repairService.removeById(id));
    }

    // 修改报修
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody @Valid Repair repair) {
        if (repair.getStatus() != null && repair.getStatus() == 2 && repair.getFinishTime() == null) {
            repair.setFinishTime(LocalDateTime.now());
        }
        return R.ok(repairService.updateById(repair));
    }

    // 报修列表
    @GetMapping("/list")
    public R<List<Repair>> list() {
        return R.ok(repairService.list());
    }

    // 报修详情
    @GetMapping("/detail/{id}")
    public R<Repair> getById(@PathVariable Integer id) {
        return R.ok(repairService.getById(id));
    }
}