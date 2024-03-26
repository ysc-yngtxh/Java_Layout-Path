package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-26 13:12
 * @apiNote TODO
 */
@Aspect
@Component
public class ExampleAop {
    @Before("execution(* com.example.service.impl.CustomServiceImpl.definition(..))")
    public void beforeAdvice(JoinPoint jp){
        System.out.println("进入切面 " + jp.getSignature().getName() + "()");
    }
}
