package com.example;

import com.example.mybatis.entity.Student;
import com.example.mybatis.factory.MapperProxyFactory;
import com.example.mybatis.mapper.StudentMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MybatisBottomLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisBottomLayerApplication.class, args);
        StudentMapper mapper = MapperProxyFactory.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.queryUser("敏敏", 22);
        System.out.println(studentList);
    }

}
