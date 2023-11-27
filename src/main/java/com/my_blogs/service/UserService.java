package com.my_blogs.service;

import com.my_blogs.dto.UserAddDTO;
import com.my_blogs.dto.UserLoginDTO;
import com.my_blogs.entity.User;

public interface UserService {
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 新增用户
     * @param userAddDTO
     * @return
     */
    void addUser(UserAddDTO userAddDTO);
}
