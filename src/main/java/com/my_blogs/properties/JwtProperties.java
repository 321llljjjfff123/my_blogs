package com.my_blogs.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "blogs.jwt")
@Data
public class JwtProperties {
    /**
     * 管理员登录jwt令牌配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;


    /**
     * 用户登录jwt相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
}
