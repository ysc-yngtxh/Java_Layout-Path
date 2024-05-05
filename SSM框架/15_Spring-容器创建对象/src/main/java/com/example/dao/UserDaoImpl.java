package com.example.dao;

import com.example.domain.SysUser;

public class UserDaoImpl implements UserDao {
    @Override
    public void insertUser(SysUser user) {
        System.out.println("user插入到sql数据库 " + user);
    }
}
