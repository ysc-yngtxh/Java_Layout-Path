package com.example.mapper;

import com.example.annotation.IgnoreTenantId;
import com.example.entity.User;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * (User)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2023-08-25 00:00:00
 */
public interface UserMapper {

	/**
	 * 通过ID查询单条数据,忽略 tenant_id 作为查询条件
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
	@IgnoreTenantId
	User queryByIdIgnoreTenant(Long id);

	@MapKey("userName")
	Map<String, User> queryAll();

	/**
	 * 统计总行数
	 *
	 * @param user 查询条件
	 * @return 总行数
	 */
	long count(User user);

	/**
	 * 新增数据
	 *
	 * @param user 实例对象
	 * @return 影响行数
	 */
	int insert(User user);

	/**
	 * 批量新增数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<User> 实例对象列表
	 * @return 影响行数
	 */
	int insertBatch(@Param("entities") List<User> entities);

	/**
	 * 批量新增或按主键更新数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<User> 实例对象列表
	 * @return 影响行数
	 * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
	 */
	int insertOrUpdateBatch(@Param("entities") List<User> entities);

	/**
	 * 修改数据
	 *
	 * @param user 实例对象
	 * @return 影响行数
	 */
	int update(User user);

	/**
	 * 修改数据
	 *
	 * @param user 实例对象
	 * @return 影响行数
	 */
	int fullTableUpdate(User user);

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 影响行数
	 */
	int deleteById(Long id);

}
