package com.example.service;

import com.example.dao.StudentMapper;
import com.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContext;

import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Map<String, Student> queryStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Student> queryStudentByIdMap(Integer id) {
        return studentMapper.selectByPrimaryKeyMap(id);
    }


}
