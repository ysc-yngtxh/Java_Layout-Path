package com.example.mapper;

import com.example.entity.Timetable;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (Timetable)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:00:00
 */
public interface TimetableMapper {

	/**
	 * 通过ID查询单条数据
	 */
	Timetable queryById(Integer id);

	/**
	 * 统计总行数
	 */
	long count(Timetable timetable);

	/**
	 * 新增数据
	 */
	int insert(Timetable timetable);

	/**
	 * 批量新增数据（MyBatis原生foreach方法）
	 */
	int insertBatch(@Param("entities") List<Timetable> entities);

	/**
	 * 批量新增或按主键更新数据（MyBatis原生foreach方法）
	 */
	int insertOrUpdateBatch(@Param("entities") List<Timetable> entities);

	/**
	 * 修改数据
	 */
	int update(Timetable timetable);

	/**
	 * 通过主键删除数据
	 */
	int deleteById(Integer id);
}
