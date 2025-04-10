package com.example.service;

import com.example.pojo.Student;
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
    public void addStudent(Student Student) {
        studentDao.insertStudent(Student);
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
}
