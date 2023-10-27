package com.example;

import com.example.Service.StudentService;
import com.example.dao.StudentDao;
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
        StudentDao dao = (StudentDao) ac.getBean("studentDao");
        Student student = new Student();
        student.setId(1019);
        student.setName("我喜欢曹玉敏");
        student.setEmail("lijingjing@163.com");
        student.setAge(2020919);
        int nums = dao.insertStudent(student);
        // spring和mybatis整合在一起使用，事务是自动提交的。无需执行SqlSession.commit()
        System.out.println("nums=" + nums);
    }

    @Test
    @Transactional
    public void test03(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService service = (StudentService) ac.getBean("studentService");

        List<Student> students = service.queryStudents();
        List<Student> students2 = service.queryStudents();
        for (Student stu : students) {
            System.out.println(stu);
        }
    }
}
