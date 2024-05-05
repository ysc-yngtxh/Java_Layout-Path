package com.example.service;

import com.example.pojo.User;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-15 00:24
 * @apiNote TODO
 */
public interface UserService {
    User queryById(Integer id);

    void insertUser(User user);
}
