package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;

import java.util.List;

public interface StudentDao {

    int insertStudent(Student student);

    List<Student> selectStudents();
}
