package com.bjpowernode.ba01;

import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 */
@Component(value = "Service1")
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        System.out.println("====目标方法doSome()====");
    }
}
