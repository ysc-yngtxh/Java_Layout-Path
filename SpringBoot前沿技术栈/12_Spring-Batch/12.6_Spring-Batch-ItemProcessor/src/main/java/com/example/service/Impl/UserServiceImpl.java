package com.example.service.Impl;

import com.example.dto.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-14 08:20
 * @apiNote TODO
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public User toUpperCase(User user) {
		user.setName(user.getName().toUpperCase());
		return user;
	}
}
