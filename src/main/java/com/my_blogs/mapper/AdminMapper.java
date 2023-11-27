package com.my_blogs.mapper;

import com.my_blogs.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

        /**
         * 根据用户名查询
         *
         * @param username
         * @return
         */
        @Select("SELECT * from my_blogs.admin where username = #{username} and password=#{password}")
        Admin getByUsername(String username, String password);

}
