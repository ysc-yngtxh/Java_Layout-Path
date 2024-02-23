package com.example;

import com.example.service.HelloService;
import com.example.service.impl.HelloServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // HelloService service = new HelloServiceImpl();
        // service.sayHello("张三");

        // 使用多态机制，new出类对象
        HelloService target = new HelloServiceImpl();
        // 使用反射机制获取到目标方法的代理类Method
        Method method = HelloService.class.getMethod("sayHello", String.class);
        // 通过Method可以执行sayHello方法调用
        /*
           invoke是Method类中的一个方法。表示执行方法的调用
           参数：
               1、Object,表示对象的，要执行这个对象的方法
               2、Object...args,方法执行时的参数值
           返回值：
               Object:方法执行后的返回值
         */
        // 表达的意思就是  执行target对象sayHello,参数是李四
        Object ret = method.invoke(target, "李四");
    }
}
