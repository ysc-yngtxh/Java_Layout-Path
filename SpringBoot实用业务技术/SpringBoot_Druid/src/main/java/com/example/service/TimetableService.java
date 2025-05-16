package com.example.service;

import com.example.entity.Timetable;

/**
 * (Timetable)表服务接口
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:00:00
 */
public interface TimetableService {

	/**
	 * 通过ID查询单条数据
	 */
	Timetable queryById(Integer id);
}
