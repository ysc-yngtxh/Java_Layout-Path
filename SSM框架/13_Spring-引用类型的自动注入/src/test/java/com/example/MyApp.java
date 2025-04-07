package com.example;

import com.example.pojo.SysUser;
import com.example.repository.Student;
import com.example.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {

    @Test
    public void test() {
        // 从Spring容器中获取UserService类型的对象，mapper、service都是Spring管理的
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service = (UserService) ctx.getBean("userService");

        SysUser user = new SysUser();
        user.setName("lisi");
        user.setAge(23);
        service.addUser(user);
    }

    // 引用类型的自动注入
    @Test
    public void test01() {
        // 从Spring容器中获取UserService类型的对象，mapper、service都是Spring管理的
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student service = (Student) ac.getBean("myStudent");

        System.out.println("Student对象:" + service);
    }

    // 多个配置文件的使用
    @Test
    public void test02() {
        // 从spring容器中获取UserService类型的对象。mapper、service都是spring管理的
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean/spring-total.xml");
        Student service = (Student) ac.getBean("myStudent");

        System.out.println("Student对象" + service);
    }
}
