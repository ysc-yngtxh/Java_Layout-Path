package com.bjpowernode.dubbo.service;

import com.bjpowernode.dubbo.domain.User;

public interface UserService {

    //根据用户标识获取用户信息
    User queryUserById(Integer id,String username);
}
