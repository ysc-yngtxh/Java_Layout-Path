package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.entity.Consumer;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * (Consumer)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2023-08-28 22:30:20
 */
public interface ConsumerMapper extends BaseMapper<Consumer> {

	// 通过注解的方式使用SQL语句自定义CRUD条件的方法
	@Select("""
			select * from tb_consumer where user_name like #{user_name}
			and (age < #{age} or #{notNull} is not null)
			""")
	List<Consumer> selectCustomAnnotationParam(@Param("user_name") String userName,
	                                           @Param("age") Integer age,
	                                           @Param("notNull") String notNull);

	/**
	 * ew 是mapper方法里的 @Param(Constants.WRAPPER) Wrapper queryWrapper对象
	 * <p>
	 * ${ew.sqlSelect} 获取 Wrapper对象中 Select字段部分
	 * <p>
	 * 首先通过${ew.emptyOfWhere}判断queryWrapper对象是否存在where条件(类似JSparser解析sql语句是否存在where条件)，有的话拼接sql语句
	 * ${ew.SqlSegment} 是 sql条件语句
	 * ${ew.customSqlSegment} 是 WHERE + sql条件语句
	 * <p>
	 * 使用${ew.sqlSegment} 如果是连表查询且查询条件是连表的字段则需在service层拼接查询条件时字段前指定别名
	 */
	@Select("select ${ew.sqlSelect} from tb_consumer ${ew.customSqlSegment}")
	List<Consumer> selectCustomAnnotationWrapper(@Param(Constants.WRAPPER) Wrapper<Consumer> wrapper);
}
