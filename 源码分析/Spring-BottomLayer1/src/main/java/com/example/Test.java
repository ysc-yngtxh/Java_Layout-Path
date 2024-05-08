package com.example;

import com.example.service.UserService;
import com.spring.YoujiawankuConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        YoujiawankuConfigApplicationContext applicationContext =
                new YoujiawankuConfigApplicationContext(AppConfig.class);

        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));

        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }

}
