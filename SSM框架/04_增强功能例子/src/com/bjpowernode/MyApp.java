package com.bjpowernode;

import com.bjpowernode.handler.MyInvocationHandler;
import com.bjpowernode.service.GoNong;
import com.bjpowernode.service.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {

    public static void main(String[] args) {

        GoNong goNeng = new GoNong();
        InvocationHandler handler = new MyInvocationHandler(goNeng);

        HelloService proxy = (HelloService) Proxy.newProxyInstance(goNeng.getClass().getClassLoader(),
                goNeng.getClass().getInterfaces(),
                handler);
        int num = proxy.print("市场");
        System.out.println("我们期望的 num="+num);
    }
}
