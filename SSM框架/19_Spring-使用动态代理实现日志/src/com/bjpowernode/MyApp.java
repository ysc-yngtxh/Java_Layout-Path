package com.example;

import com.example.handler.MyInvocationHandler;
import com.example.service.SomeService;
import com.example.service.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {
    public static void main(String[] args) {
        
        //使用jdk的Proxy创建对象
        //创建目标对象
        SomeService target = new SomeServiceImpl();
        
        //创建InvocationHandler对象
        InvocationHandler handler = new MyInvocationHandler(target);
        
        //使用Proxy创建代理
        SomeService proxy = (SomeService) Proxy.newProxyInstance(
                        target.getClass().getClassLoader(),
                        target.getClass().getInterfaces(),
                        handler);
        
        //通过代理执行方法，会调用handler中的invoke()
        proxy.doSome();
        System.out.println("===========================");
        proxy.doOther();
    }
}
