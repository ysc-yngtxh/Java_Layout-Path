package com.example.service;

import com.example.domain.Student;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentService {

    int addStudent(Student student);
    List<Student> findStudents();
}
