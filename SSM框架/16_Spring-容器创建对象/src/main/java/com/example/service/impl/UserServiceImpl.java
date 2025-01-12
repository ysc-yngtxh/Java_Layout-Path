package com.example.service.impl;

import com.example.mapper.UserDao;
import com.example.pojo.SysUser;
import com.example.service.UserService;

public class UserServiceImpl implements UserService {

    // 引用类型，在xml文件给属性赋值，要求属性需要有set方法
    private UserDao dao;

    public void setDao(UserDao dao){
        this.dao = dao;
    }

    @Override
    public void addUser(SysUser user) {
        dao.insertUser(user);
    }
}
