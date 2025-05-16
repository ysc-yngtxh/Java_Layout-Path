package com.example.service;

import com.example.entity.User;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-08-25 00:00:00
 */
public interface UserService {

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	User queryById(Long id);

	/**
	 * 通过ID查询单条数据,忽略 tenant_id 作为查询条件
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	User queryByIdIgnoreTenant(Long id);

	/**
	 * 查询所有数据
	 *
	 * @return Map<String, User>
	 */
	Map<String, User> queryAll();

	/**
	 * 新增数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	User insert(User user);

	/**
	 * 修改数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	User update(User user);

	/**
	 * 修改数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	User fullTableUpdate(User user);

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	boolean deleteById(Long id);
}
