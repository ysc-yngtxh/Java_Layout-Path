package com.example.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 必须实现InvocationHandler接口，完成代理类要做的功能(1、调用目标方法。2、功能增强)
public class MySellHandler implements InvocationHandler {

    private Object target;

    // 动态代理：目标对象是活动的，不是固定的，需要传入进来。传入的是谁，就给谁创建代理
    public MySellHandler(Object target) {
        // 给目标对象赋值
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 向厂家发送订单，告诉厂家，我买了U盘，厂家发货
        // float price = target.sell(amount);  // 厂家的价格
        Object res = method.invoke(target, args); // 执行目标方法

        // 商家 需要加价，也就是代理要增加价格
        // price = price+25; // 增强功能，代理类在完成目标类方法调用后，增强了功能。
        if (res != null) {
            float price = (Float) res;
            price = price + 25;
            res = price;
        }

        // 在目标类的方法调用后，你做的其他功能，都是增强的意思
        System.out.println("淘宝商家，给你一个优惠券，或者红包");

        // 增加的价格
        return res;
    }
}
