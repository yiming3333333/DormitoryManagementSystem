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
import jakarta.validation.constraints.Size;

@TableName("admin")
public class Admin {
    @TableId(
            type = IdType.AUTO
    )
    private Integer adminId;
    private @NotBlank(
            message = "用户名不能为空"
    ) @Size(
            max = 20,
            message = "用户名不超过20字"
    ) String username;
    private String password;
    private Integer role;
    @TableLogic
    private Integer deleted;

    public Admin() {
    }

    public Integer getAdminId() {
        return this.adminId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Integer getRole() {
        return this.role;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setAdminId(final Integer adminId) {
        this.adminId = adminId;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }

    public void setDeleted(final Integer deleted) {
        this.deleted = deleted;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Admin)) {
            return false;
        } else {
            Admin other = (Admin)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$adminId = this.getAdminId();
                Object other$adminId = other.getAdminId();
                if (this$adminId == null) {
                    if (other$adminId != null) {
                        return false;
                    }
                } else if (!this$adminId.equals(other$adminId)) {
                    return false;
                }

                Object this$role = this.getRole();
                Object other$role = other.getRole();
                if (this$role == null) {
                    if (other$role != null) {
                        return false;
                    }
                } else if (!this$role.equals(other$role)) {
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

                Object this$username = this.getUsername();
                Object other$username = other.getUsername();
                if (this$username == null) {
                    if (other$username != null) {
                        return false;
                    }
                } else if (!this$username.equals(other$username)) {
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
        return other instanceof Admin;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $adminId = this.getAdminId();
        result = result * 59 + ($adminId == null ? 43 : $adminId.hashCode());
        Object $role = this.getRole();
        result = result * 59 + ($role == null ? 43 : $role.hashCode());
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getAdminId();
        return "Admin(adminId=" + var10000 + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", role=" + this.getRole() + ", deleted=" + this.getDeleted() + ")";
    }
}
