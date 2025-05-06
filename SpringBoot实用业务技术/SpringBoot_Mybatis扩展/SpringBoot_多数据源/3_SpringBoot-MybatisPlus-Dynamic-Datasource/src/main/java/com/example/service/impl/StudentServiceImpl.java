package com.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.StudentMapper;
import com.example.entity.Student;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    // 使用 @DS注解，通过AOP进行切换数据源。@DS注解可写在类上和方法上
    // 当类上出现 @DS注解，表示该类的所有方法都使用类上指定的数据源；如果不想指定所有的方法数据源，就写在方法上
    // 当类上和方法上都有 @DS注解时，使用的是方法上的 @DS数据源
    @DS("master")
    public List<Student> findMasterByIds() {
        List<Integer> ids = Arrays.asList(1, 3, 5, 8, 10);
        return baseMapper.selectBatchIds(ids);
    }

    // 进行换数据源slave_1的查询。
    // 但是换的数据源slave_1里依旧有一张 student表，且字段属性一致
    @DS("slave_1")
    public List<Student> findSlave_1ByIds() {
        List<Integer> ids = Arrays.asList(1, 3, 5, 8, 10);
        return baseMapper.selectBatchIds(ids);
    }
}
