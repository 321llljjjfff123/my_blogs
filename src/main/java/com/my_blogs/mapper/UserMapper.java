package com.my_blogs.mapper;

import com.my_blogs.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    User getByUsernameAndPassword(String username, String password);

    @Select("SELECT * from user where username = #{username} and password=#{password}")
    User get(String username, String password);

    @Insert("")
    void insert(User user);
}
