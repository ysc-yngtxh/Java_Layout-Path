package com.example;

import com.example.repository.Student;
import com.example.pojo.SysUser;
import com.example.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {

    @Test
    public void test01() {
        // 从Spring容器中获取UserService类型的对象，mapper、service都是Spring管理的
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student service = (Student) ac.getBean("myStudent");

        System.out.println("Student对象 " + service);
    }

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
}
