package com.example.proxy.jdk;

import com.example.service.ContainInterfaceService;
import com.example.service.impl.ContainInterfaceServiceImpl;
import java.lang.reflect.Proxy;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:09
 * @apiNote TODO 手动实现 JDK 动态代理
 */
public class TestExecutor1 {

    public static void main(String[] args) {
        ContainInterfaceService containInterfaceService = new ContainInterfaceServiceImpl();
        JDKProxy jdkProxy = new JDKProxy(containInterfaceService);

        ContainInterfaceService instance = (ContainInterfaceService) Proxy.newProxyInstance(
                containInterfaceService.getClass().getClassLoader(),
                containInterfaceService.getClass().getInterfaces(),
                jdkProxy
        );
        instance.definition();

        System.out.println(instance.getClass().getName());
    }
}
