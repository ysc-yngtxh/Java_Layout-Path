package com.example.springbootshirojwt.mapper;

import com.example.springbootshirojwt.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface ShiroJwtMapper extends Mapper<User> {

    @Select("select id,name,pwd,perms from user where name=#{name}")
    User queryByName(@Param("name") String name);

}
