package com.example.service;

import com.example.pojo.SSMStudent;

import java.util.List;

public interface StudentService {

    void addStudent(SSMStudent SSMStudent);

    List<SSMStudent> queryStudents();
}
