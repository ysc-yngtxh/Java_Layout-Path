package com.example;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(context.getBean("orderService"));
    }
}
