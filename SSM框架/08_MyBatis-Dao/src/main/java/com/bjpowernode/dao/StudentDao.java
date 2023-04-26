package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

    List<Student> selectStudents();

    int insertStudent(Student student);
}
