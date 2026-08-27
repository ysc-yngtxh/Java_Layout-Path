package com.example.dubbo.service.Impl;

import com.example.dubbo.domain.User;
import com.example.dubbo.service.UserService;

/**
 * @author 游家纨绔
 */
public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("曹玉敏");
        return user;
    }

    @Override
    public Integer queryAllUserCount() {
        return 52;
    }
}
