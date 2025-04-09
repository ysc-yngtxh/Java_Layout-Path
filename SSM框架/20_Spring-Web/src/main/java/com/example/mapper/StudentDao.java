package com.example.mapper;

import com.example.pojo.SSMStudent;

import java.util.List;

public interface StudentDao {

    int insertStudent(SSMStudent SSMStudent);

    List<SSMStudent> selectStudents();
}
