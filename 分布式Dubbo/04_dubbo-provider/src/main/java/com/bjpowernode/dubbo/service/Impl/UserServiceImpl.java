package com.bjpowernode.dubbo.service.Impl;

import com.bjpowernode.dubbo.domain.User;
import com.bjpowernode.dubbo.service.UserService;

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
