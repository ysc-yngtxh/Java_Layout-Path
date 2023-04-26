package com.bjpowernode.service.impl;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.domain.SysUser;
import com.bjpowernode.service.UserService;

public class UserServiceImpl implements UserService {

    //引用类型，在xml文件给属性赋值，要求属性需要有set方法
    private UserDao dao = null;

    public void setDao(UserDao dao){
        this.dao = dao;
    }

    @Override
    public void addUser(SysUser user) {
        dao.insertUser(user);
    }
}
