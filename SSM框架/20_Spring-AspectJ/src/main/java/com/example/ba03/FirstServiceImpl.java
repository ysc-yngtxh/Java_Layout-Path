package com.example.ba03;

import com.example.ba02.Student;

public class FirstServiceImpl implements FirstService {


    @Override
    public String First(String name, Integer age) {
        System.out.println("====业务方法First()====");
        System.out.println(name + ";" + age);
        return "First";
    }
}
