//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.dorm.common.R;
import com.dorm.entity.Building;
import com.dorm.entity.Dormitory;
import com.dorm.service.BuildingService;
import com.dorm.service.DormitoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/building"})
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DormitoryService dormitoryService;

    public BuildingController() {
    }

    @PostMapping({"/add"})
    public R<Boolean> add(@RequestBody @Valid Building building) {
        return R.ok(this.buildingService.save(building));
    }

    @DeleteMapping({"/delete/{id}"})
    public R<Boolean> delete(@PathVariable Integer id) {
        long count = ((LambdaQueryChainWrapper)this.dormitoryService.lambdaQuery().eq(Dormitory::getBuildingId, id)).count();
        return count > 0L ? R.error("该楼栋下存在宿舍，无法删除") : R.ok(this.buildingService.removeById(id));
    }

    @PutMapping({"/update"})
    public R<Boolean> update(@RequestBody @Valid Building building) {
        return R.ok(this.buildingService.updateById(building));
    }

    @GetMapping({"/list"})
    public R<List<Building>> list() {
        return R.ok(this.buildingService.list());
    }

    @GetMapping({"/detail/{id}"})
    public R<Building> getById(@PathVariable Integer id) {
        return R.ok((Building)this.buildingService.getById(id));
    }
}
