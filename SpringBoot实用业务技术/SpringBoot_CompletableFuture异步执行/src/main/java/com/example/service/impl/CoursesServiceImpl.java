package com.example.service.impl;

import com.example.entity.Courses;
import com.example.mapper.CoursesDao;
import com.example.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Courses)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-08-18 23:30:00
 */
@Service
public class CoursesServiceImpl implements CoursesService {

	@Autowired
	private CoursesDao coursesDao;

	@Override
	public Courses getOne(Integer id) {
		Courses courses = coursesDao.selectById(id);
		return courses;
	}
}
