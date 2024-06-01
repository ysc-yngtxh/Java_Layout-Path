package com.example;

import com.example.demo1.AppScanPath1;
import com.example.demo2.AppScanPath2;
import com.example.demo2.service.UserService;
import com.example.spring.ConfigApplicationContext1;
import com.example.spring.ConfigApplicationContext2;
import org.junit.jupiter.api.Test;

public class SpringBottomLayer1Tests {

    @Test
    public void applicationContext1() {
        // ClassPathXmlApplicationContext 表示从类路径加载配置文件
        // AnnotationConfigApplicationContext 表示从注解加载配置文件

        // 简单模拟实现自定义的 Spring 容器 -- ConfigApplicationContext1
        // AppScanPath1.class 表示容器扫描 demo1 路径
        ConfigApplicationContext1 applicationContext1 =
                new ConfigApplicationContext1(AppScanPath1.class);

        // 通过容器获取 Bean。当使用的是原型模式时，每次获取到的 Bean 都是新的实例
        System.out.println(applicationContext1.getBean("userService1"));
        System.out.println(applicationContext1.getBean("userService1"));
        System.out.println(applicationContext1.getBean("userService1"));
    }

    @Test
    public void applicationContext2() {
        // AppScanPath2.class 表示容器扫描 demo1 路径
        ConfigApplicationContext2 applicationContext2 =
                new ConfigApplicationContext2(AppScanPath2.class);

        // 代理对象
        UserService userService = (UserService) applicationContext2.getBean("userServiceImpl");
        System.out.println("代理对象为：" + userService.getClass().getName());
        // 调用代理对象的方法
        userService.test();
    }
}
