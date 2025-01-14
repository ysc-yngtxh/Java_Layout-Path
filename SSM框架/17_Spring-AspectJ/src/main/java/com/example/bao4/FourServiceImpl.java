package com.example.bao4;

public class FourServiceImpl implements FourService {

    @Override
    public String like(String name, Integer time) {
        System.out.println("=====like()方法的执行====");
        return "小曹，我like你!";
    }
}
