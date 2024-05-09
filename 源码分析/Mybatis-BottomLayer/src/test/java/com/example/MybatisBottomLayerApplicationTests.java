package com.example;

import com.example.mybatis1.entity.Student;
import com.example.mybatis1.factory.MapperProxyFactory1;
import com.example.mybatis1.mapper.StudentMapper1;
import com.example.mybatis2.factory.MapperProxyFactory2;
import com.example.mybatis2.mapper.StudentMapper2;
import com.example.mybatis3.factory.MapperProxyFactory3;
import com.example.mybatis3.mapper.StudentMapper3;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisBottomLayerApplicationTests {
    // 测试方法，测试功能
    @Test
    public void mybatis1() {
        StudentMapper1 mapper = MapperProxyFactory1.getMapper(StudentMapper1.class);
        List<Student> studentList = mapper.queryUser("敏敏", 22);
        System.out.println(studentList);
    }

    @Test
    public void mybatis2() {
        StudentMapper2 mapper = MapperProxyFactory2.getMapper(StudentMapper2.class);
        System.out.println(mapper.queryUser("游诗成", 25));
        System.out.println(mapper.queryUserById(7));
    }

    @Test
    public void mybatis3() {
        StudentMapper3 mapper = MapperProxyFactory3.getMapper(StudentMapper3.class);
        System.out.println(mapper.queryUser("游诗成", 25));
        System.out.println(mapper.queryUserById(7));
    }
}
