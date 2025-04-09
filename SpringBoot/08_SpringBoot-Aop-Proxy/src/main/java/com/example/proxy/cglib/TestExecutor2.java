package com.example.proxy.cglib;

import com.example.service.impl.DirectServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:10
 * @apiNote TODO 手动实现 Cglib 动态代理
 */
public class TestExecutor2 {

    public static void main(String[] args) {
        DirectServiceImpl directService = new DirectServiceImpl();
        CglibInterceptor cglibInterceptor = new CglibInterceptor(directService);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(directService.getClass());
        enhancer.setCallback(cglibInterceptor);
        DirectServiceImpl service = (DirectServiceImpl) enhancer.create();
        service.direct();

        System.out.println(service.getClass().getName());
    }
}
