//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@TableName("repair")
public class Repair {
    @TableId(
            type = IdType.AUTO
    )
    private Integer repairId;
    private @NotNull(
            message = "宿舍ID不能为空"
    ) Integer dormId;
    private @NotNull(
            message = "学生ID不能为空"
    ) Integer studentId;
    private @NotBlank(
            message = "问题描述不能为空"
    ) @Size(
            max = 500,
            message = "描述不超过500字"
    ) String description;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;
    @TableLogic
    private Integer deleted;

    public Repair() {
    }

    public Integer getRepairId() {
        return this.repairId;
    }

    public Integer getDormId() {
        return this.dormId;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getStatus() {
        return this.status;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public LocalDateTime getFinishTime() {
        return this.finishTime;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setRepairId(final Integer repairId) {
        this.repairId = repairId;
    }

    public void setDormId(final Integer dormId) {
        this.dormId = dormId;
    }

    public void setStudentId(final Integer studentId) {
        this.studentId = studentId;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setCreateTime(final LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setFinishTime(final LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public void setDeleted(final Integer deleted) {
        this.deleted = deleted;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Repair)) {
            return false;
        } else {
            Repair other = (Repair)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$repairId = this.getRepairId();
                Object other$repairId = other.getRepairId();
                if (this$repairId == null) {
                    if (other$repairId != null) {
                        return false;
                    }
                } else if (!this$repairId.equals(other$repairId)) {
                    return false;
                }

                Object this$dormId = this.getDormId();
                Object other$dormId = other.getDormId();
                if (this$dormId == null) {
                    if (other$dormId != null) {
                        return false;
                    }
                } else if (!this$dormId.equals(other$dormId)) {
                    return false;
                }

                Object this$studentId = this.getStudentId();
                Object other$studentId = other.getStudentId();
                if (this$studentId == null) {
                    if (other$studentId != null) {
                        return false;
                    }
                } else if (!this$studentId.equals(other$studentId)) {
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

                Object this$description = this.getDescription();
                Object other$description = other.getDescription();
                if (this$description == null) {
                    if (other$description != null) {
                        return false;
                    }
                } else if (!this$description.equals(other$description)) {
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

                Object this$finishTime = this.getFinishTime();
                Object other$finishTime = other.getFinishTime();
                if (this$finishTime == null) {
                    if (other$finishTime != null) {
                        return false;
                    }
                } else if (!this$finishTime.equals(other$finishTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Repair;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $repairId = this.getRepairId();
        result = result * 59 + ($repairId == null ? 43 : $repairId.hashCode());
        Object $dormId = this.getDormId();
        result = result * 59 + ($dormId == null ? 43 : $dormId.hashCode());
        Object $studentId = this.getStudentId();
        result = result * 59 + ($studentId == null ? 43 : $studentId.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $finishTime = this.getFinishTime();
        result = result * 59 + ($finishTime == null ? 43 : $finishTime.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getRepairId();
        return "Repair(repairId=" + var10000 + ", dormId=" + this.getDormId() + ", studentId=" + this.getStudentId() + ", description=" + this.getDescription() + ", status=" + this.getStatus() + ", createTime=" + String.valueOf(this.getCreateTime()) + ", finishTime=" + String.valueOf(this.getFinishTime()) + ", deleted=" + this.getDeleted() + ")";
    }
}
