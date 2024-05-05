package com.example;

import com.example.service.impl.UsbKingFactory;
import com.example.handler.MySellHandler;
import com.example.service.UsbSell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ExecutorMain {

    public static void main(String[] args) {
        // 创建代理对象，使用Proxy
        // 1、创建目标对象，多态对象
        UsbSell target = new UsbKingFactory();
        // 2、创建InvocationHandler对象
        InvocationHandler handler = new MySellHandler(target);
        // 3、创建代理对象
        // 其实也很好理解。正常加载一个类需要通过类加载器去加载吧，调用类方法还需要知道是哪个类吧
        // 所以 target.getClass().getClassLoader() 获取到目标类的类加载器
        // target.getClass().getInterfaces() 获取到目标类的接口类；
        // 这里之所以传参是目标类的接口类，是因为接口类可以高扩展更多功能。而且我们是通过多态获取的对象，照样能获取到具体的目标类
        // handler 则是我们进行功能增强的实现类
        UsbSell proxy = (UsbSell) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler
        );
        // 4、通过代理执行方法
        float price = proxy.sell(1);
        System.out.println("通过动态代理对象，调用方法" + price);
    }
}
