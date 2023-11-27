package com.my_blogs.service.impl;

import com.my_blogs.NotFountException;
import com.my_blogs.dto.UserAddDTO;
import com.my_blogs.dto.UserLoginDTO;
import com.my_blogs.entity.User;
import com.my_blogs.mapper.UserMapper;
import com.my_blogs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        log.info(username + password);
        // 根据用户名和密码查询数据库中的数据

        // 目前是数据库存储的就是MD5加密的
        // 前端发送的是未加密的，进行加密后判断
        // 以后改为前端加密后再发送过来

        // 密码对比
        // 对前端传过来的明文密码进行MD5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 这个查询数据库有问题
        User user2 = userMapper.getByUsernameAndPassword(username, password);
        // 这个可以
        User user = userMapper.get(username, password);

         if(user == null) {
            // 账户不存在
            throw new NotFountException("用户"+username+"的账户不存在");
        }

        if(!password.equals(user.getPassword())) {
            throw new NotFountException("密码不正确");
        }
        return user;
    }

    public void addUser(UserAddDTO userAddDTO) {
        User user = new User();

        // 对象拷贝
        BeanUtils.copyProperties(userAddDTO, user);

        // 将发送过来的密码进行加密处理
        user.setPassword(DigestUtils.md5DigestAsHex(userAddDTO.getPassword().getBytes()));

        // 设置创建时间，以及更新时间
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userMapper.insert(user);
    }

}
