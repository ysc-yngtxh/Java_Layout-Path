package com.example.dubbo.service;

import com.example.dubbo.domain.User;

/**
 * @author 游家纨绔
 */
public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id) {

        User user = new User();
        user.setId(id);
        user.setUsername("曹玉敏");
        user.setAge(22);
        return user;
    }
}
