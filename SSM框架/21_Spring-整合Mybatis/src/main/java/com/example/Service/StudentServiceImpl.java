package com.example.Service;

import com.example.domain.Student;
import com.example.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    // 使用set注入，赋值
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public List<Student> queryStudents() {
        return studentDao.selectStudents();
    }
}
