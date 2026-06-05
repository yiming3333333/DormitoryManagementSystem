//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.dto;

import jakarta.validation.constraints.NotBlank;

public class AdminLoginDTO {
    private @NotBlank(
            message = "用户名不能为空"
    ) String username;
    private @NotBlank(
            message = "密码不能为空"
    ) String password;

    public AdminLoginDTO() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AdminLoginDTO)) {
            return false;
        } else {
            AdminLoginDTO other = (AdminLoginDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
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
        return other instanceof AdminLoginDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getUsername();
        return "AdminLoginDTO(username=" + var10000 + ", password=" + this.getPassword() + ")";
    }
}
