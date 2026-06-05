//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.entity.Dormitory;
import com.dorm.mapper.DormitoryMapper;
import com.dorm.service.DormitoryService;
import com.dorm.vo.DormitoryVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {
    @Autowired
    private DormitoryMapper dormitoryMapper;

    public DormitoryServiceImpl() {
    }

    public List<DormitoryVO> getDormitoryList() {
        return this.dormitoryMapper.selectDormitoryList();
    }
}
