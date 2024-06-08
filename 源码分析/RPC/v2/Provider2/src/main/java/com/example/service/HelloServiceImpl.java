package com.example.service;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-07 下午7:04
 * @apiNote TODO Hello实现类
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
