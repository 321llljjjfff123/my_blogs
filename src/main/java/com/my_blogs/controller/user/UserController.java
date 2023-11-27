package com.my_blogs.controller.user;

import com.my_blogs.dto.AdminLoginDTO;
import com.my_blogs.dto.UserAddDTO;
import com.my_blogs.dto.UserLoginDTO;
import com.my_blogs.entity.User;
import com.my_blogs.properties.JwtProperties;
import com.my_blogs.service.UserService;
import com.my_blogs.utils.JwtUtil;
import com.my_blogs.vo.UserLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    public static final String USER_ID = "user";

    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

    @PostMapping("/login")
    public UserLoginVO login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("员工登录：{}", userLoginDTO);
        User user = userService.login(userLoginDTO);

        // 登录成功，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, user.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username((user.getUsername()))
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .token(token)
                .build();
        return userLoginVO;
    }


    @GetMapping(" /insert")
    @ApiOperation(value = "新增用户")
    public String addUser(@RequestBody UserAddDTO userAddDTO) {
        return "successfully";
    }

}
