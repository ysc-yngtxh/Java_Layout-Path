package com.example;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {
    @Test
    public void test() {
        String xmlPath = "applicationContext.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(context.getBean("aService"));
    }
}
