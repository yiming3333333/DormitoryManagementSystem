//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.vo;

public class DormitoryVO {
    private Integer dormId;
    private Integer buildingId;
    private String buildingName;
    private String dormNumber;
    private Integer capacity;
    private Integer currentCount;
    private Integer status;

    public DormitoryVO() {
    }

    public Integer getDormId() {
        return this.dormId;
    }

    public Integer getBuildingId() {
        return this.buildingId;
    }

    public String getBuildingName() {
        return this.buildingName;
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

    public void setDormId(final Integer dormId) {
        this.dormId = dormId;
    }

    public void setBuildingId(final Integer buildingId) {
        this.buildingId = buildingId;
    }

    public void setBuildingName(final String buildingName) {
        this.buildingName = buildingName;
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

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DormitoryVO)) {
            return false;
        } else {
            DormitoryVO other = (DormitoryVO)o;
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

                Object this$buildingName = this.getBuildingName();
                Object other$buildingName = other.getBuildingName();
                if (this$buildingName == null) {
                    if (other$buildingName != null) {
                        return false;
                    }
                } else if (!this$buildingName.equals(other$buildingName)) {
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
        return other instanceof DormitoryVO;
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
        Object $buildingName = this.getBuildingName();
        result = result * 59 + ($buildingName == null ? 43 : $buildingName.hashCode());
        Object $dormNumber = this.getDormNumber();
        result = result * 59 + ($dormNumber == null ? 43 : $dormNumber.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getDormId();
        return "DormitoryVO(dormId=" + var10000 + ", buildingId=" + this.getBuildingId() + ", buildingName=" + this.getBuildingName() + ", dormNumber=" + this.getDormNumber() + ", capacity=" + this.getCapacity() + ", currentCount=" + this.getCurrentCount() + ", status=" + this.getStatus() + ")";
    }
}
