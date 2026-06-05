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

@TableName("dormitory")
public class Dormitory {
    @TableId(
            type = IdType.AUTO
    )
    private Integer dormId;
    private @NotNull(
            message = "所属楼栋不能为空"
    ) Integer buildingId;
    private @NotBlank(
            message = "宿舍号不能为空"
    ) @Size(
            max = 10,
            message = "宿舍号不超过10字"
    ) String dormNumber;
    private @NotNull(
            message = "容量不能为空"
    ) @Min(
            value = 1L,
            message = "容量至少1人"
    ) Integer capacity;
    private Integer currentCount;
    private Integer status;
    @TableLogic
    private Integer deleted;

    public Dormitory() {
    }

    public Integer getDormId() {
        return this.dormId;
    }

    public Integer getBuildingId() {
        return this.buildingId;
    }

    public String getDormNumber() {
        return this.dormNumber;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public Integer getCurrentCount() {
        return this.currentCount;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setDormId(final Integer dormId) {
        this.dormId = dormId;
    }

    public void setBuildingId(final Integer buildingId) {
        this.buildingId = buildingId;
    }

    public void setDormNumber(final String dormNumber) {
        this.dormNumber = dormNumber;
    }

    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    public void setCurrentCount(final Integer currentCount) {
        this.currentCount = currentCount;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setDeleted(final Integer deleted) {
        this.deleted = deleted;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Dormitory)) {
            return false;
        } else {
            Dormitory other = (Dormitory)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$dormId = this.getDormId();
                Object other$dormId = other.getDormId();
                if (this$dormId == null) {
                    if (other$dormId != null) {
                        return false;
                    }
                } else if (!this$dormId.equals(other$dormId)) {
                    return false;
                }

                Object this$buildingId = this.getBuildingId();
                Object other$buildingId = other.getBuildingId();
                if (this$buildingId == null) {
                    if (other$buildingId != null) {
                        return false;
                    }
                } else if (!this$buildingId.equals(other$buildingId)) {
                    return false;
                }

                Object this$capacity = this.getCapacity();
                Object other$capacity = other.getCapacity();
                if (this$capacity == null) {
                    if (other$capacity != null) {
                        return false;
                    }
                } else if (!this$capacity.equals(other$capacity)) {
                    return false;
                }

                Object this$currentCount = this.getCurrentCount();
                Object other$currentCount = other.getCurrentCount();
                if (this$currentCount == null) {
                    if (other$currentCount != null) {
                        return false;
                    }
                } else if (!this$currentCount.equals(other$currentCount)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
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

                Object this$dormNumber = this.getDormNumber();
                Object other$dormNumber = other.getDormNumber();
                if (this$dormNumber == null) {
                    if (other$dormNumber != null) {
                        return false;
                    }
                } else if (!this$dormNumber.equals(other$dormNumber)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Dormitory;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dormId = this.getDormId();
        result = result * 59 + ($dormId == null ? 43 : $dormId.hashCode());
        Object $buildingId = this.getBuildingId();
        result = result * 59 + ($buildingId == null ? 43 : $buildingId.hashCode());
        Object $capacity = this.getCapacity();
        result = result * 59 + ($capacity == null ? 43 : $capacity.hashCode());
        Object $currentCount = this.getCurrentCount();
        result = result * 59 + ($currentCount == null ? 43 : $currentCount.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $dormNumber = this.getDormNumber();
        result = result * 59 + ($dormNumber == null ? 43 : $dormNumber.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getDormId();
        return "Dormitory(dormId=" + var10000 + ", buildingId=" + this.getBuildingId() + ", dormNumber=" + this.getDormNumber() + ", capacity=" + this.getCapacity() + ", currentCount=" + this.getCurrentCount() + ", status=" + this.getStatus() + ", deleted=" + this.getDeleted() + ")";
    }
}
