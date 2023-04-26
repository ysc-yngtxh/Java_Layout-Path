package com.bjpowernode;

import com.bjpowernode.dao.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {


    @Test
    public void test01(){

        //从spring容器中获取UserService类型的对象，dao,service都是spring管理的
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student service = (Student) ac.getBean("myStudent");

        System.out.println("Student对象"+service);
    }


}
