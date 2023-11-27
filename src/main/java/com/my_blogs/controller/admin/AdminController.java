package com.my_blogs.controller.admin;


import com.my_blogs.dto.AdminLoginDTO;
import com.my_blogs.entity.Admin;
import com.my_blogs.properties.JwtProperties;
import com.my_blogs.service.AdminService;
import com.my_blogs.utils.JwtUtil;
import com.my_blogs.vo.AdminLoginVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    public static final String ADMIN_ID = "adm";

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public AdminLoginVO login(@RequestBody AdminLoginDTO adminLoginDTO) {
        log.info("管理员登录：{}", adminLoginDTO);
        Admin admin = adminService.login(adminLoginDTO);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(ADMIN_ID, admin.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .name(admin.getUsername())
                .token(token)
                .build();



        return adminLoginVO;
    }


    @GetMapping("/logout")
    @ApiOperation(value = "管理员退出登录")
    public String logout() {
        return "successfully";
    }
}
