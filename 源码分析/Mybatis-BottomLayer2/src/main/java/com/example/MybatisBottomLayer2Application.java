package com.example;

import com.example.mybatis.factory.MapperProxyFactory;
import com.example.mybatis.mapper.StudentMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisBottomLayer2Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisBottomLayer2Application.class, args);
        StudentMapper mapper = MapperProxyFactory.getMapper(StudentMapper.class);
        System.out.println(mapper.queryUser("游诗成", 25));
        System.out.println(mapper.queryUserById(7));
    }

}
