package com.example.aop3;

public class ThreeServiceImpl implements ThreeService {

    @Override
    public String First(String name, Integer age) {
        System.out.println("====业务方法First()====");
        System.out.println(name + ";" + age);
        return "First";
    }
}
