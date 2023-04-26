package com.bjpowernode.dubbo.service;

import com.bjpowernode.dubbo.domain.User;

/**
 * @author 游家纨绔
 */
public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id) {

        User user = new User();
        user.setId(id);
        user.setUsername("李晶晶");
        user.setAge(22);
        return user;
    }
}
