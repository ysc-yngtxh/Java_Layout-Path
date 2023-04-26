package com.system.service.impl;

import com.system.dao.CoursesDao;
import com.system.pojo.Courses;
import com.system.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/16 16:29
 */
@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesDao coursesDao;

    //根据用户id查找用户基本信息
    @Override
    public List<Courses> selectUserDate(int id) {
        List<Courses> courses = coursesDao.selectDate(id);
        return courses;
    }

}
