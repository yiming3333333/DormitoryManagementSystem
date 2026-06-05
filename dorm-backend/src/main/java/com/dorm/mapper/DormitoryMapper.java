//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dorm.entity.Dormitory;
import com.dorm.vo.DormitoryVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DormitoryMapper extends BaseMapper<Dormitory> {
    @Select({"SELECT d.*, b.building_name as buildingName FROM dormitory d LEFT JOIN building b ON d.building_id = b.building_id"})
    List<DormitoryVO> selectDormitoryList();
}
