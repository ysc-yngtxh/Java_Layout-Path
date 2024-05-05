package com.example;

import com.example.dao.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {

    // 引用类型的自动注入
    @Test
    public void test01(){
        // 从spring容器中获取UserService类型的对象，dao,service都是spring管理的
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student service = (Student) ac.getBean("myStudent");

        System.out.println("Student对象:" + service);
    }

    // 多个配置文件的使用
    @Test
    public void test02(){
        // 从spring容器中获取UserService类型的对象。dao、service都是spring管理的
        ApplicationContext ac = new ClassPathXmlApplicationContext("bao/total.xml");
        Student service = (Student) ac.getBean("myStudent");

        System.out.println("Student对象" + service);
    }
}
