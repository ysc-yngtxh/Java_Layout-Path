package com.example.service;

/**
 * @author 游家纨绔
 */
public class SomeServiceImpl implements SomeService {

    // 对象的创建就会调用其构造方法
    public SomeServiceImpl() {
        System.out.println("SomeServiceImpl的无参数构造方法");
    }

    @Override
    public void doSome() {
        System.out.println("执行了SomeServiceImpl的doSome()方法");
    }
}
