package com.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * (Student)表服务实现类
 * @author 游家纨绔
 * @since 2023-09-02 21:59:40
 */
@DS("master")
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @DS("master")
    public List<Student> findByMasterIds() {
        List<Integer> ids = Arrays.asList(1, 3, 5, 8, 10);
        return baseMapper.selectBatchIds(ids);
    }

    @DS("slave_1")
    public List<Student> findByUserIds() {
        List<Integer> ids = Arrays.asList(1, 3, 5, 8, 10);
        return baseMapper.selectBatchIds(ids);
    }
}

