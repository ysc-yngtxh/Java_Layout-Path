package com.example.jdk;

import org.springframework.stereotype.Component;

@Component
public class CglibByServiceImpl implements CglibService{
    @Override
    public String like(String name, Integer time) {
        System.out.println("=====like()方法的执行====");
        return "曹玉敏，我喜欢你!";
    }
}
