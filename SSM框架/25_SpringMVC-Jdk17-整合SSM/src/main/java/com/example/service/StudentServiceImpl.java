package com.example.service;

import com.example.mapper.StudentDao;
import com.example.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 游家纨绔
 */
@Service
public class StudentServiceImpl implements StudentService {

    // 引用类型自动注入@Autowired,@Resource
    @Autowired
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public List<Student> findStudents() {
        return studentDao.selectStudents();
    }
}
