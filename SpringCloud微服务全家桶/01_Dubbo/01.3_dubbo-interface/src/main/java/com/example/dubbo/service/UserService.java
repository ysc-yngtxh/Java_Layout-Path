package com.example.dubbo.service;

import com.example.dubbo.domain.User;

public interface UserService {

    //根据用户标识获取用户信息
    User queryUserById(Integer id);

    //查询用户总人数
    Integer queryAllUserCount();
}
