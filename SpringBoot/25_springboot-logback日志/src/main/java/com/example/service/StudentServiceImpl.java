package com.example.service;

import com.example.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Integer queryStudentCount() {
        return studentMapper.selectStudentCount();
    }
}
