package com.example.mybatis.mapper;

import com.example.mybatis.annotation.Select;
import com.example.mybatis.entity.User;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-04 23:52
 * @apiNote TODO 自定义Mapper
 */
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    public List<User> queryUserById(Integer id);

}
