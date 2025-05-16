package com.example.service;

import com.example.entity.Courses;

/**
 * (Courses)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-08-18 23:30:00
 */
public interface CoursesService {

	Courses getOne(Integer id);
}
