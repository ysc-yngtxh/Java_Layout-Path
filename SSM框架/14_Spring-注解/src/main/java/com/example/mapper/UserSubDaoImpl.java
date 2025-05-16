package com.example.mapper;

import com.example.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository("userSubDao")
public class UserSubDaoImpl implements UserDao {

	@Override
	public void insertUser(SysUser user) {
		System.out.println("user插入到oracle数据库" + user);
	}

}
