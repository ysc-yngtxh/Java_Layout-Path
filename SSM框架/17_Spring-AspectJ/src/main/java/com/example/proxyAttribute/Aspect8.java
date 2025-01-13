package com.example.proxyAttribute;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspect8 {

    @Around(value="execution(* *..AttrServiceImpl.doSomething())")
    public Object MyText7(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("来，看看喜欢曹玉敏的前置通知是怎么做的。");
        pjp.proceed();
        return "";
    }
}
