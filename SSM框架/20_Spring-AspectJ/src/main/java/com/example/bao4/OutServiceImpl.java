package com.example.bao4;

public class OutServiceImpl implements OutService {
    @Override
    public String like(String name, Integer time) {
        System.out.println("=====like()方法的执行====");
        return "曹玉敏，我喜欢你!";
    }
}
