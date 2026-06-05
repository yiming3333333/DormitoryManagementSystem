//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dorm.entity.Dormitory;
import com.dorm.vo.DormitoryVO;
import java.util.List;

public interface DormitoryService extends IService<Dormitory> {
    List<DormitoryVO> getDormitoryList();
}
