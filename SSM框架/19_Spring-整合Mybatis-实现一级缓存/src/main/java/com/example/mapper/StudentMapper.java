package com.example.mapper;

import com.example.pojo.Student;

import java.util.List;

public interface StudentMapper {
    int insertStudent(Student student);
    List<Student> selectStudents();
}
