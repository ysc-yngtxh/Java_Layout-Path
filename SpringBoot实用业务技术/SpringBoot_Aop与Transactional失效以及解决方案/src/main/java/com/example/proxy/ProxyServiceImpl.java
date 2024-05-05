package com.example.proxy;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 14:39
 * @apiNote TODO
 */
@Service
public class ProxyServiceImpl implements ProxyService {
    @Override
    public void useInterface() {
        System.out.println("调用useInterface()方法");
    }

    @Override
    public void useSigInterface() {
        System.out.println("调用useSigInterface()方法");
        // 直接调用useInterface()方法，这里使用的对象是this，也就是当前对象。
        // 而Aop是基于动态代理实现的，这里没有代理对象，因此对saveUser()方法的切面编程是不生效执行的
        this.useInterface();
    }

    @Override
    public void useAllInterface() {
        System.out.println("调用useAllInterface()方法");
        // AopContext.currentProxy() 是 Spring 框架中的一个静态方法，用于获取当前正在执行的代理对象。
        // 这个方法是在组件内部调用的，目的是让当前组件能够访问自己的代理对象，从而在方法内部调用目标方法或其他切入点。
        // 将 currentProxy 强制转换为 proxyService 类型。这个时候useInterface()方法的切面编程是会生效执行
        ProxyService currentProxy = (ProxyService) AopContext.currentProxy();
        currentProxy.useInterface();
    }
}