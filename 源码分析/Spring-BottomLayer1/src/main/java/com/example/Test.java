package com.example;

import com.spring.YoujiawankuConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        YoujiawankuConfigApplicationContext applicationContext =
                new YoujiawankuConfigApplicationContext(AppConfig.class);
        Object userService = applicationContext.getBean("userService");
    }

}
