package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-07-09 09:00:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public boolean check(String username, String password) {
		User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
		if (Objects.nonNull(user)) {
			return user.getPassWord().equals(password);
		}
		return false;
	}

	@Override
	public List<User> queryPage(Integer page, Integer size) {
		List<User> userList = userMapper.selectList(null);
		List<List<User>> partition = Lists.partition(userList, size);
		if (partition.size() < page) {
			return Collections.emptyList();
		}
		return partition.get(page - 1);
	}

	@Override
	public Integer countAll() {
		Long aLong = userMapper.selectCount(null);
		return aLong.intValue();
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateById(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteById(id);
	}

}
