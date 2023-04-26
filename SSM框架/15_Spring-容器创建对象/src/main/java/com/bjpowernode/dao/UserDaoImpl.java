package com.bjpowernode.dao;

import com.bjpowernode.domain.SysUser;

public class UserDaoImpl implements UserDao {

    @Override
    public void insertUser(SysUser user) {
        System.out.println("user插入到sql数据库");
    }
}
