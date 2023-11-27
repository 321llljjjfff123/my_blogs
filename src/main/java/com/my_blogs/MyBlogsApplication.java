package com.my_blogs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.my_blogs.mapper")
public class MyBlogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogsApplication.class, args);
    }

}
