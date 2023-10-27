package com.example.service;

import com.example.dao.StudentMapper;
import com.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int queryStudentById(Student student) {
        int i = studentMapper.updateByPrimaryKeySelective(student);
        return i;
    }

    public Student query(Integer id){
        return studentMapper.selectByPrimaryKey(id);
    }
}