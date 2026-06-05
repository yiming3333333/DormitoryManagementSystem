//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@TableName("building")
public class Building {
    @TableId(
            type = IdType.AUTO
    )
    private Integer buildingId;
    private @NotBlank(
            message = "楼栋名称不能为空"
    ) @Size(
            max = 20,
            message = "楼栋名称不超过20字"
    ) String buildingName;
    private @NotNull(
            message = "楼层数不能为空"
    ) @Min(
            value = 1L,
            message = "楼层数至少1层"
    ) Integer floorCount;
    private @NotNull(
            message = "房间数不能为空"
    ) @Min(
            value = 1L,
            message = "房间数至少1间"
    ) Integer roomCount;
    private LocalDateTime createTime;
    @TableLogic
    private Integer deleted;

    public Building() {
    }

    public Integer getBuildingId() {
        return this.buildingId;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public Integer getFloorCount() {
        return this.floorCount;
    }

    public Integer getRoomCount() {
        return this.roomCount;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setBuildingId(final Integer buildingId) {
        this.buildingId = buildingId;
    }

    public void setBuildingName(final String buildingName) {
        this.buildingName = buildingName;
    }

    public void setFloorCount(final Integer floorCount) {
        this.floorCount = floorCount;
    }

    public void setRoomCount(final Integer roomCount) {
        this.roomCount = roomCount;
    }

    public void setCreateTime(final LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setDeleted(final Integer deleted) {
        this.deleted = deleted;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Building)) {
            return false;
        } else {
            Building other = (Building)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$buildingId = this.getBuildingId();
                Object other$buildingId = other.getBuildingId();
                if (this$buildingId == null) {
                    if (other$buildingId != null) {
                        return false;
                    }
                } else if (!this$buildingId.equals(other$buildingId)) {
                    return false;
                }

                Object this$floorCount = this.getFloorCount();
                Object other$floorCount = other.getFloorCount();
                if (this$floorCount == null) {
                    if (other$floorCount != null) {
                        return false;
                    }
                } else if (!this$floorCount.equals(other$floorCount)) {
                    return false;
                }

                Object this$roomCount = this.getRoomCount();
                Object other$roomCount = other.getRoomCount();
                if (this$roomCount == null) {
                    if (other$roomCount != null) {
                        return false;
                    }
                } else if (!this$roomCount.equals(other$roomCount)) {
                    return false;
                }

                Object this$deleted = this.getDeleted();
                Object other$deleted = other.getDeleted();
                if (this$deleted == null) {
                    if (other$deleted != null) {
                        return false;
                    }
                } else if (!this$deleted.equals(other$deleted)) {
                    return false;
                }

                Object this$buildingName = this.getBuildingName();
                Object other$buildingName = other.getBuildingName();
                if (this$buildingName == null) {
                    if (other$buildingName != null) {
                        return false;
                    }
                } else if (!this$buildingName.equals(other$buildingName)) {
                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Building;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $buildingId = this.getBuildingId();
        result = result * 59 + ($buildingId == null ? 43 : $buildingId.hashCode());
        Object $floorCount = this.getFloorCount();
        result = result * 59 + ($floorCount == null ? 43 : $floorCount.hashCode());
        Object $roomCount = this.getRoomCount();
        result = result * 59 + ($roomCount == null ? 43 : $roomCount.hashCode());
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $buildingName = this.getBuildingName();
        result = result * 59 + ($buildingName == null ? 43 : $buildingName.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getBuildingId();
        return "Building(buildingId=" + var10000 + ", buildingName=" + this.getBuildingName() + ", floorCount=" + this.getFloorCount() + ", roomCount=" + this.getRoomCount() + ", createTime=" + String.valueOf(this.getCreateTime()) + ", deleted=" + this.getDeleted() + ")";
    }
}
