package com.example.cglib;

import org.springframework.stereotype.Service;

@Service("cglibService")
public class CglibServiceImpl {

    public String like(String name, Integer time) {
        System.out.println("=====like()方法的执行====");
        return "小曹，我like你!";
    }
}
