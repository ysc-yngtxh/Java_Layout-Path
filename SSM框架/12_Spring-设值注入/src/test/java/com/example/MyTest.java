package com.example;

import com.example.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class MyTest {

    // 简单类型的set注入
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student st = (Student) ac.getBean("myStudent");
        System.out.println("student=" + st);
    }

    // -02-
    @Test
    public void test02(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student st = (Student) ac.getBean("myStudentBy");
        System.out.println("student=" + st);
    }

    // 构造注入
    @Test
    public void test03(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student st1 = (Student) ac.getBean("myStudentByIn1");
        System.out.println("student=" + st1);

        Student st2 = (Student) ac.getBean("myStudentByIn2");
        System.out.println("student=" + st2);

        Student st3 = (Student) ac.getBean("myStudentByIn3");
        System.out.println("student=" + st3);
    }

    // 创建File，使用构造注入。可以创建文件对象
    @Test
    public void test04(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        File st = (File) ac.getBean("myFile");
        System.out.println("File = " + st);
        System.out.println("File = " + st.getName());
    }
}