package com.dorm.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.dorm.common.R;
import com.dorm.entity.CheckIn;
import com.dorm.entity.Dormitory;
import com.dorm.entity.Repair;
import com.dorm.service.CheckInService;
import com.dorm.service.DormitoryService;
import com.dorm.service.RepairService;
import com.dorm.vo.DormitoryVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {

    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private CheckInService checkInService;
    @Autowired
    private RepairService repairService;

    /**
     * 带楼栋信息宿舍列表
     */
    @GetMapping("/listWithBuilding")
    public R<List<DormitoryVO>> listWithBuilding() {
        return R.ok(dormitoryService.getDormitoryList());
    }

    /**
     * 宿舍基础列表
     */
    @GetMapping("/list")
    public R<List<Dormitory>> list() {
        return R.ok(dormitoryService.list());
    }

    /**
     * 根据id查询宿舍详情
     */
    @GetMapping("/detail/{id}")
    public R<Dormitory> getById(@PathVariable Integer id) {
        return R.ok(dormitoryService.getById(id));
    }

    /**
     * 新增宿舍
     */
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody @Valid Dormitory dormitory) {
        return R.ok(dormitoryService.save(dormitory));
    }

    /**
     * 修改宿舍
     */
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody @Valid Dormitory dormitory) {
        return R.ok(dormitoryService.updateById(dormitory));
    }

    /**
     * 删除宿舍：校验在住学生、未完结报修
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        // 统计在住学生：dormId+状态0
        long checkInCount = checkInService.lambdaQuery()
                .eq(CheckIn::getDormId, id)
                .eq(CheckIn::getStatus, 0)
                .count();

        if (checkInCount > 0) {
            return R.error("该宿舍存在在住学生，无法删除");
        }

        // 统计未完成报修
        long repairCount = repairService.lambdaQuery()
                .eq(Repair::getDormId, id)
                .eq(Repair::getStatus, 0)
                .count();

        if (repairCount > 0) {
            return R.error("该宿舍存在未完成的报修，无法删除");
        }
        // 校验通过执行删除
        return R.ok(dormitoryService.removeById(id));
    }
}