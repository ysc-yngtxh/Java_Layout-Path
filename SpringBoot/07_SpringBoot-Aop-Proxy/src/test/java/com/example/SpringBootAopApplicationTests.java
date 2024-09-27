package com.example;

import com.example.service.ContainInterfaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAopApplicationTests {

    @Autowired
    private ContainInterfaceService containInterfaceService;

    @Test
    void contextLoads1() {
        System.out.println("获取的service对象实例为代理对象：" + containInterfaceService.getClass().getName());
        System.out.println(containInterfaceService.definition());
    }

    @Test
    void contextLoads2() {
        System.out.println("获取的service对象实例为代理对象：" + containInterfaceService.getClass().getName());
        System.out.println(containInterfaceService.aopFailure());
    }

    @Test
    void contextLoads3() {
        System.out.println("获取的service对象实例为代理对象：" + containInterfaceService.getClass().getName());
        System.out.println(containInterfaceService.aopContextExecutor());
    }
}
