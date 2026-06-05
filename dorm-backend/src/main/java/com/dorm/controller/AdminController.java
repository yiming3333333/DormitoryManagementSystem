//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.dorm.common.R;
import com.dorm.dto.AdminLoginDTO;
import com.dorm.entity.Admin;
import com.dorm.service.AdminService;
import com.dorm.util.JwtUtil;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin"})
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;

    public AdminController() {
    }

    @PostMapping({"/add"})
    public R<Boolean> add(@RequestBody @Valid Admin admin) {
        if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
            admin.setPassword(this.encoder.encode(admin.getPassword()));
            return R.ok(this.adminService.save(admin));
        } else {
            return R.error("密码不能为空");
        }
    }

    @DeleteMapping({"/delete/{id}"})
    public R<Boolean> delete(@PathVariable Integer id) {
        return R.ok(this.adminService.removeById(id));
    }

    @PutMapping({"/update"})
    public R<Boolean> update(@RequestBody @Valid Admin admin) {
        if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
            admin.setPassword(this.encoder.encode(admin.getPassword()));
        } else {
            admin.setPassword((String)null);
        }

        return R.ok(this.adminService.updateById(admin));
    }

    @GetMapping({"/list"})
    public R<List<Admin>> list() {
        List<Admin> list = this.adminService.list();
        list.forEach((admin) -> admin.setPassword((String)null));
        return R.ok(list);
    }

    @GetMapping({"/detail/{id}"})
    public R<Admin> getById(@PathVariable Integer id) {
        Admin admin = (Admin)this.adminService.getById(id);
        if (admin != null) {
            admin.setPassword((String)null);
        }

        return R.ok(admin);
    }

    @PostMapping({"/login"})
    public R<Map<String, Object>> login(@RequestBody @Valid AdminLoginDTO loginDTO) {
        Admin dbAdmin = (Admin)((LambdaQueryChainWrapper)this.adminService.lambdaQuery().eq(Admin::getUsername, loginDTO.getUsername())).one();
        if (dbAdmin != null && this.encoder.matches(loginDTO.getPassword(), dbAdmin.getPassword())) {
            String token = this.jwtUtil.generateToken(dbAdmin.getAdminId(), dbAdmin.getUsername(), dbAdmin.getRole());
            Map<String, Object> result = new HashMap();
            result.put("token", token);
            result.put("adminId", dbAdmin.getAdminId());
            result.put("username", dbAdmin.getUsername());
            result.put("role", dbAdmin.getRole());
            return R.ok(result);
        } else {
            return R.error("账号或密码错误");
        }
    }
}
