package com.example;

import com.example.handler.MyInvocationHandler;
import com.example.service.GoNong;
import com.example.service.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {
    public static void main(String[] args) {
        // 1、创建目标对象，多态对象
        HelloService goNong = new GoNong();
        // 2、创建InvocationHandler对象
        InvocationHandler handler = new MyInvocationHandler(goNong);
        // 3、创建代理对象
        // 其实也很好理解。正常加载一个类需要通过类加载器去加载吧，调用类方法还需要知道是哪个类吧
        // 所以 factory.getClass().getClassLoader() 获取到目标类的类加载器
        // factory.getClass().getInterfaces() 获取到目标类的接口类；
        // 这里之所以传参是目标类的接口类，是因为接口类可以高扩展更多功能。而且我们是通过多态获取的对象，照样能获取到具体的目标类
        // handler 则是我们进行功能增强的实现类
        HelloService proxy = (HelloService) Proxy.newProxyInstance(
                goNong.getClass().getClassLoader(),
                goNong.getClass().getInterfaces(),
                handler
        );
        int num = proxy.print("市场");
        System.out.println("我们期望的 num=" + num);
    }
}