package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-08-25 00:10:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	@Override
	public User queryById(Long id) {
		return this.userMapper.queryById(id);
	}

	/**
	 * 通过ID查询单条数据,忽略 tenant_id 作为查询条件
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	@Override
	public User queryByIdIgnoreTenant(Long id) {
		return this.userMapper.queryByIdIgnoreTenant(id);
	}

	@Override
	public Map<String, User> queryAll() {
		return this.userMapper.queryAll();
	}

	/**
	 * 新增数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	@Override
	public User insert(User user) {
		this.userMapper.insert(user);
		return user;
	}

	/**
	 * 修改数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	@Override
	public User update(User user) {
		this.userMapper.update(user);
		return this.queryById(user.getId());
	}

	/**
	 * 修改数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	@Override
	public User fullTableUpdate(User user) {
		this.userMapper.fullTableUpdate(user);
		return this.queryById(user.getId());
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Long id) {
		return this.userMapper.deleteById(id) > 0;
	}
}
