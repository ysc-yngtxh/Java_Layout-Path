package com.example.service;

import com.example.domain.Student;

import java.util.Map;

public interface StudentService {

    Map<String, Student> queryStudentByIdMap(Integer id);

    Map<String, Student> queryStudentByIdStudent(Integer id);

    void streamingQuery1();

    void streamingQuery2();

    void selectStudent();
}
