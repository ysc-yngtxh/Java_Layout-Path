package com.example;

import com.example.Service.StudentService;
import com.example.mapper.StudentMapper;
import com.example.domain.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MyApp {
    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] names = ac.getBeanDefinitionNames();
        for (String na : names) {
            System.out.println("容器中对象的名称" + na + "|" + ac.getBean(na));
        }
    }

    @Test
    public void test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper dao = (StudentMapper) ac.getBean("studentDao");
        Student student = new Student();
        student.setId(1020);
        student.setName("我喜欢曹玉敏");
        student.setEmail("lijingjing@163.com");
        student.setAge(2020919);
        int nums = dao.insertStudent(student);
        // spring和mybatis整合在一起使用，事务是自动提交的。无需执行SqlSession.commit()
        System.out.println("nums=" + nums);
    }


    // Mybatis的一级缓存
    // Spring 的事务管理创建一个新的代理对象来执行这个方法，而这个代理对象通常会关联一个新的 SqlSession。
    @Test
    @Transactional
    public void test03(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService service = (StudentService) ac.getBean("studentService");


        List<Student> students = service.selectStudents();
        List<Student> students2 = service.selectStudents();
        for (Student stu : students) {
            System.out.println(stu);
        }
    }
}
