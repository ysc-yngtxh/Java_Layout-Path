package com.example.dao;

import com.example.domain.Student;

import java.util.List;

public interface StudentDao {

    List<Student> selectStudents();

    int insertStudent(Student student);
}
