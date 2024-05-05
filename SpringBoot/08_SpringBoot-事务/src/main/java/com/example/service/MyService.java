package com.example.service;

import com.example.domain.Student;

public interface MyService {

    int queryStudentById(Student student);

    Student query(Integer id);
}
