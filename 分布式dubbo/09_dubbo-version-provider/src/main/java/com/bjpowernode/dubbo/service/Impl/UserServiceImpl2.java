package com.bjpowernode.dubbo.service.Impl;

import com.bjpowernode.dubbo.domain.User;
import com.bjpowernode.dubbo.service.UserService;

public class UserServiceImpl2 implements UserService {


    @Override
    public User queryUserById(Integer id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username+"-2");
        return user;
    }
}
