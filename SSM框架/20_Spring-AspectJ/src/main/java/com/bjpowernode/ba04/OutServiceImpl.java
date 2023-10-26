package com.example.ba04;

public class OutServiceImpl implements OutService {

    @Override
    public String like(String name,Integer time) {
        System.out.println("=====like()方法的执行====");
        return "李晶晶，我喜欢你!";
    }
}
