package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CoursesDao;
import com.example.entity.Courses;
import com.example.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Courses)表服务实现类
 *
 * @author makejava
 * @since 2023-08-18 23:25:59
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

