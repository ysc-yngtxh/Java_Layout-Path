package com.example.mapper;

import com.example.pojo.SysUser;

public class UserDaoImpl implements UserDao {
    @Override
    public void insertUser(SysUser user) {
        System.out.println("user插入到sql数据库 " + user);
    }
}
