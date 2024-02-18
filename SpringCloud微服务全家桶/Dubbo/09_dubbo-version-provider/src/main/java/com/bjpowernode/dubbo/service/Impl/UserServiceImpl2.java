package com.example.dubbo.service.Impl;

import com.example.dubbo.domain.User;
import com.example.dubbo.service.UserService;

public class UserServiceImpl2 implements UserService {


    @Override
    public User queryUserById(Integer id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username+"-2");
        return user;
    }
}
