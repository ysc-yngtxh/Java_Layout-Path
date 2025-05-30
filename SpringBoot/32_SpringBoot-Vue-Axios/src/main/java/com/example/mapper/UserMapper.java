package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (User)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2023-07-09 09:10:00
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 批量新增或按主键更新数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<User> 实例对象列表
	 * @return 影响行数
	 * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
	 */
	int insertOrUpdateBatch(@Param("entities") List<User> entities);
}
