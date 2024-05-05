package com.example;

import com.example.service.CustomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAopApplicationTests {

    @Autowired
    private CustomService customService;

    @Test
    void contextLoads1() {
        System.out.println("获取的service对象实例为代理对象：" + customService.getClass().getName());
        System.out.println(customService.definition());
    }

    @Test
    void contextLoads2() {
        System.out.println("获取的service对象实例为代理对象：" + customService.getClass().getName());
        System.out.println(customService.aopFailure());
    }

    @Test
    void contextLoads3() {
        System.out.println("获取的service对象实例为代理对象：" + customService.getClass().getName());
        System.out.println(customService.aopContextExecutor());
    }
}
