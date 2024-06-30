package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-07-01 06:28
 * @apiNote TODO
 */
@Aspect
@Component
public class AspectAop {

    @Before(value = "execution(* *..impl.TimetableServiceImpl.queryById(Integer))")
    public void before(JoinPoint joinPoint) {
        System.out.println("进入切面 " + joinPoint.getSignature().getName() + "()");
    }
}
