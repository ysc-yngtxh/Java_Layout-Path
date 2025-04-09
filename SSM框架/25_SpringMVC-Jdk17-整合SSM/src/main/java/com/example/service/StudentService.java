package com.example.service;

import com.example.pojo.SSMStudent;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentService {

    int addStudent(SSMStudent SSMStudent);
    List<SSMStudent> findStudents();
}
