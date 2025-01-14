package com.example.mapper;

import com.example.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public void insertUser(SysUser user) {
        System.out.println("user插入到mysql数据库" + user);
    }
}
