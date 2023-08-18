package com.example.service;

import com.example.entity.Courses;

/**
 * (Courses)表服务接口
 *
 * @author makejava
 * @since 2023-08-18 23:25:59
 */
public interface CoursesService {

    Courses getOne(Integer id);
}

