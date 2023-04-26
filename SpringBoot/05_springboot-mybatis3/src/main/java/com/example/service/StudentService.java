package com.example.service;

import com.example.domain.Student;

import java.util.Map;

public interface StudentService {

    Map<String, Student> queryStudentById(Integer id);

    Map<String, Student> queryStudentByIdMap(Integer id);
}
