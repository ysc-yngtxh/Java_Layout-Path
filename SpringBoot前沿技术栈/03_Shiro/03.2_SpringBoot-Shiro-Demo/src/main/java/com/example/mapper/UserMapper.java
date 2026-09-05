package com.example.mapper;

import com.example.pojo.User;

/**
 * @author 游家纨绔
 * @dateTime 2026-09-05 19:14
 * @apiNote TODO
 */
public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    User selectByName(String name);
}
