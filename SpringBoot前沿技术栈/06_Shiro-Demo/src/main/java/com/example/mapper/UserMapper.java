package com.example.mapper;

import com.example.pojo.User;

/**
 * @Entity generator.pojo.Students
 */
public interface UserMapper {


    User selectByPrimaryKey(Long id);

    User selectByName(String name);

}




