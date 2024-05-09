package com.example;

import com.example.demo1.AppConfigApplication1;
import com.example.demo2.AppConfigApplication2;
import com.example.demo2.service.UserService2;
import com.example.spring.YoujiawankuConfigApplicationContext1;
import com.example.spring.YoujiawankuConfigApplicationContext2;
import org.junit.jupiter.api.Test;

public class ApplicationTests {

    @Test
    public void applicationContext1() {
        // 简单模拟实现自定义的 Spring 容器 (YoujiawankuConfigApplicationContext1)
        // AppConfigApplication1.class 表示容器扫描 demo1 路径
        YoujiawankuConfigApplicationContext1 applicationContext1 =
                new YoujiawankuConfigApplicationContext1(AppConfigApplication1.class);

        // 通过容器获取 Bean。当使用的是原型模式时，每次获取到的 Bean 都是新的实例
        System.out.println(applicationContext1.getBean("userService1"));
        System.out.println(applicationContext1.getBean("userService1"));
        System.out.println(applicationContext1.getBean("userService1"));
    }

    @Test
    public void applicationContext2() {
        // 扫描 demo2 路径
        YoujiawankuConfigApplicationContext2 applicationContext2 =
                new YoujiawankuConfigApplicationContext2(AppConfigApplication2.class);

        UserService2 userService2 = (UserService2) applicationContext2.getBean("userService2");
        userService2.test();
    }
}
