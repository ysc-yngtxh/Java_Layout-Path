package com.system.dao;

import com.system.pojo.Courses;
import com.system.pojo.Registry;

import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/16 16:13
 */
public interface CoursesDao {

    //通过用户id查找课程信息
    List<Courses> selectDate(int id);
}
