package com.example.proxy.cglib;

import com.example.service.impl.DirectServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:10
 * @apiNote TODO 手动实现 Cglib 动态代理
 */
public class TestExecutor2 {

    // 这里执行的是静态main()方法，没有注入Spring容器中，切面逻辑不会生效
    public static void main(String[] args) {
        DirectServiceImpl directService = new DirectServiceImpl();
        CgLibInterceptor cglibInterceptor = new CgLibInterceptor(directService);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(directService.getClass());
        enhancer.setCallback(cglibInterceptor);
        DirectServiceImpl service = (DirectServiceImpl) enhancer.create();
        service.direct();

        System.out.println(service.getClass().getName());
    }
}
