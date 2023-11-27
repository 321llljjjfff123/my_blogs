package com.my_blogs.service.impl;

import com.my_blogs.NotFountException;
import com.my_blogs.dto.AdminLoginDTO;
import com.my_blogs.entity.Admin;
import com.my_blogs.mapper.AdminMapper;
import com.my_blogs.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


/**
 * 管理员接口实现类
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     *
     * @param adminLoginDTO
     * @return
     */
    public Admin login(AdminLoginDTO adminLoginDTO) {
        String username = adminLoginDTO.getUsername();
        String password = adminLoginDTO.getPassword();

        // 1 根据用户名和密码查询数据库中的数据
        Admin admin = adminMapper.getByUsername(username, password);
        // 目前采用对数据库的数据进行加密再和前端加密的对比
        // 后期改为将前端发送的MD5进行解密，再与数据库的进行对比
        String adminPasswdMD5 = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());

        // 处理异常，例如用户名不存在、密码不对、账户锁定
        if (admin == null) {
            // 账户不存在
            throw new NotFountException("管理员"+username+"的账户不存在");
        }

        // 密码对比
        // 对前端传过来的明文密码进行MD5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 还没想好怎么解决，数据库查询的密码不是加密的，前端发送的进行了加密，如何处理
            // 目前对查询到的数据库密码进行加密，然后对前端发送的加密进行对比

        if (!password.equals(adminPasswdMD5)) {
            log.info("数据库密码"+admin.getPassword());
            log.info("前端密码"+password);
            throw new NotFountException("密码不正确");
        }
        // 返回实体类
        return admin;
    }
}
