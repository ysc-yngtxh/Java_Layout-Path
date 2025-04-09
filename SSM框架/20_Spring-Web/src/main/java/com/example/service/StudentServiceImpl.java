package com.example.service;

import com.example.pojo.SSMStudent;
import com.example.mapper.StudentDao;

import java.util.List;

/**
 * @author 游家纨绔
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    // 使用set注入，赋值
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addStudent(SSMStudent SSMStudent) {
        studentDao.insertStudent(SSMStudent);
    }

    @Override
    public List<SSMStudent> queryStudents() {
        List<SSMStudent> ssmStudents = studentDao.selectStudents();
        return ssmStudents;
    }
}
