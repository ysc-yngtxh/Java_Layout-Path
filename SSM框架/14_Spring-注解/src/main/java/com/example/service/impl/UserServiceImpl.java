package com.example.service.impl;

import com.example.mapper.UserDao;
import com.example.pojo.SysUser;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	// 引用类型，在xml文件给属性赋值，要求属性需要有set方法
	@Autowired
	@Qualifier("userSubDao") // 这里是一定要使用@Qualifier的，因为有两个dao接口实现类是UserDao类型的，必须要使用byName去准确定义
	private UserDao dao;

	@Override
	public void addUser(SysUser user) {
		dao.insertUser(user);
	}
}
