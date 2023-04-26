package com.system.service;

import com.system.pojo.Courses;

import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/16 16:28
 */
public interface CoursesService {

    List<Courses> selectUserDate(int id);
}
