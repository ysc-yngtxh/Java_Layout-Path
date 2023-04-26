package com.bjpowernode.service;

import com.bjpowernode.domain.Student;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentService {

    int addStudent(Student student);
    List<Student> findStudents();
}
