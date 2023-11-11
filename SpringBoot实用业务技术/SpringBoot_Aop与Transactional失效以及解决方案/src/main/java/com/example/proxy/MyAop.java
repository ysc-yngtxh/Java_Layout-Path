package com.example.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 14:31
 * @apiNote TODO 切面代码
 */
@Aspect
@Component
public class MyAop {

    @Around("execution(* *..ProxyServiceImpl.useInterface())")
    public Object MyTest(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        System.out.println("执行useInterface()的Aop切面代码逻辑");
        return null;
    }
}
