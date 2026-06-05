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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@TableName("student")
public class Student {
    @TableId(
            type = IdType.AUTO
    )
    private Integer studentId;
    private @NotBlank(
            message = "学号不能为空"
    ) @Size(
            max = 20,
            message = "学号不超过20字"
    ) String studentNo;
    private @NotBlank(
            message = "姓名不能为空"
    ) @Size(
            max = 20,
            message = "姓名不超过20字"
    ) String name;
    private @NotNull(
            message = "性别不能为空"
    ) Integer gender;
    private @Pattern(
            regexp = "^1[3-9]\\d{9}$",
            message = "手机号格式不正确"
    ) String phone;
    private String password;
    private Integer status;
    @TableLogic
    private Integer deleted;

    public Student() {
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public String getStudentNo() {
        return this.studentNo;
    }

    public String getName() {
        return this.name;
    }

    public Integer getGender() {
        return this.gender;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setStudentId(final Integer studentId) {
        this.studentId = studentId;
    }

    public void setStudentNo(final String studentNo) {
        this.studentNo = studentNo;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setGender(final Integer gender) {
        this.gender = gender;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setPassword(final String password) {
        this.password = password;
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
        } else if (!(o instanceof Student)) {
            return false;
        } else {
            Student other = (Student)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$studentId = this.getStudentId();
                Object other$studentId = other.getStudentId();
                if (this$studentId == null) {
                    if (other$studentId != null) {
                        return false;
                    }
                } else if (!this$studentId.equals(other$studentId)) {
                    return false;
                }

                Object this$gender = this.getGender();
                Object other$gender = other.getGender();
                if (this$gender == null) {
                    if (other$gender != null) {
                        return false;
                    }
                } else if (!this$gender.equals(other$gender)) {
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

                Object this$studentNo = this.getStudentNo();
                Object other$studentNo = other.getStudentNo();
                if (this$studentNo == null) {
                    if (other$studentNo != null) {
                        return false;
                    }
                } else if (!this$studentNo.equals(other$studentNo)) {
                    return false;
                }

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$phone = this.getPhone();
                Object other$phone = other.getPhone();
                if (this$phone == null) {
                    if (other$phone != null) {
                        return false;
                    }
                } else if (!this$phone.equals(other$phone)) {
                    return false;
                }

                Object this$password = this.getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Student;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $studentId = this.getStudentId();
        result = result * 59 + ($studentId == null ? 43 : $studentId.hashCode());
        Object $gender = this.getGender();
        result = result * 59 + ($gender == null ? 43 : $gender.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $studentNo = this.getStudentNo();
        result = result * 59 + ($studentNo == null ? 43 : $studentNo.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $phone = this.getPhone();
        result = result * 59 + ($phone == null ? 43 : $phone.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getStudentId();
        return "Student(studentId=" + var10000 + ", studentNo=" + this.getStudentNo() + ", name=" + this.getName() + ", gender=" + this.getGender() + ", phone=" + this.getPhone() + ", password=" + this.getPassword() + ", status=" + this.getStatus() + ", deleted=" + this.getDeleted() + ")";
    }
}
