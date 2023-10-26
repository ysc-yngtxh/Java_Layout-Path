package com.example.service;

public class GoNong implements HelloService{
    @Override
    public int print(String name) {
        System.out.println("其他人写好的功能方法"); // 你又没权利去改，但你又需要增加功能
        return 2;
    }
}
