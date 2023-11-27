package com.my_blogs.intereceptor;

import com.my_blogs.context.BaseContext;
import com.my_blogs.properties.JwtProperties;
import com.my_blogs.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class jwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    public static final String ADMIN_ID = "admin";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到达是Controller的方法还是其它资源
        if(!(handler instanceof HandlerMethod)) {
            // 当前拦截到达不是动态方法，直接放行
            return true;
        }

        // 从请求头获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // 校验令牌
        try {
            log.info("jwt校验：{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long amdId = Long.valueOf(claims.get(ADMIN_ID).toString());
            log.info("当前管理员的id：{}", amdId);
            BaseContext.setCurrentId(amdId);
            // 通过放行
            return true;
        } catch (Exception ex) {
            // 不通过，响应401状态码
            response.setStatus(401);
            return false;
        }

    }

}
