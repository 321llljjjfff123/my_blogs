package com.my_blogs.service;


import com.my_blogs.dto.AdminLoginDTO;
import com.my_blogs.entity.Admin;

public interface AdminService {

    /**
     * 管理员登录接口
     * @param adminLoginDTO
     * @return
     */
    Admin login(AdminLoginDTO adminLoginDTO);

}
