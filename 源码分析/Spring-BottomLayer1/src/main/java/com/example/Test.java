package com.example;

import com.spring.YoujiawankuConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        YoujiawankuConfigApplicationContext applicationContext =
                new YoujiawankuConfigApplicationContext(AppConfig.class);
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
    }

}
