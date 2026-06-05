//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@TableName("check_in")
public class CheckIn {
    @TableId(
            type = IdType.AUTO
    )
    private Integer recordId;
    private @NotNull(
            message = "学生ID不能为空"
    ) Integer studentId;
    private @NotNull(
            message = "宿舍ID不能为空"
    ) Integer dormId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer status;
    @TableLogic
    private Integer deleted;

    public CheckIn() {
    }

    public Integer getRecordId() {
        return this.recordId;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public Integer getDormId() {
        return this.dormId;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setRecordId(final Integer recordId) {
        this.recordId = recordId;
    }

    public void setStudentId(final Integer studentId) {
        this.studentId = studentId;
    }

    public void setDormId(final Integer dormId) {
        this.dormId = dormId;
    }

    public void setCheckInDate(final LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(final LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
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
        } else if (!(o instanceof CheckIn)) {
            return false;
        } else {
            CheckIn other = (CheckIn)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$recordId = this.getRecordId();
                Object other$recordId = other.getRecordId();
                if (this$recordId == null) {
                    if (other$recordId != null) {
                        return false;
                    }
                } else if (!this$recordId.equals(other$recordId)) {
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

                Object this$dormId = this.getDormId();
                Object other$dormId = other.getDormId();
                if (this$dormId == null) {
                    if (other$dormId != null) {
                        return false;
                    }
                } else if (!this$dormId.equals(other$dormId)) {
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

                Object this$checkInDate = this.getCheckInDate();
                Object other$checkInDate = other.getCheckInDate();
                if (this$checkInDate == null) {
                    if (other$checkInDate != null) {
                        return false;
                    }
                } else if (!this$checkInDate.equals(other$checkInDate)) {
                    return false;
                }

                Object this$checkOutDate = this.getCheckOutDate();
                Object other$checkOutDate = other.getCheckOutDate();
                if (this$checkOutDate == null) {
                    if (other$checkOutDate != null) {
                        return false;
                    }
                } else if (!this$checkOutDate.equals(other$checkOutDate)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CheckIn;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $recordId = this.getRecordId();
        result = result * 59 + ($recordId == null ? 43 : $recordId.hashCode());
        Object $studentId = this.getStudentId();
        result = result * 59 + ($studentId == null ? 43 : $studentId.hashCode());
        Object $dormId = this.getDormId();
        result = result * 59 + ($dormId == null ? 43 : $dormId.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $checkInDate = this.getCheckInDate();
        result = result * 59 + ($checkInDate == null ? 43 : $checkInDate.hashCode());
        Object $checkOutDate = this.getCheckOutDate();
        result = result * 59 + ($checkOutDate == null ? 43 : $checkOutDate.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getRecordId();
        return "CheckIn(recordId=" + var10000 + ", studentId=" + this.getStudentId() + ", dormId=" + this.getDormId() + ", checkInDate=" + String.valueOf(this.getCheckInDate()) + ", checkOutDate=" + String.valueOf(this.getCheckOutDate()) + ", status=" + this.getStatus() + ", deleted=" + this.getDeleted() + ")";
    }
}
