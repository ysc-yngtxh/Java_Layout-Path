package com.example.aop;

import com.example.annotation.Validation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-26 13:12
 * @apiNote TODO
 */
@Aspect
@Component
public class AspectAop {

    // 切面方法
    @Before("execution(* com.example.service.impl.ContainInterfaceServiceImpl.definition(..))")
    public void beforeAdvice(JoinPoint jp) {
        System.out.println("进入切面 " + jp.getSignature().getName() + "()");
    }

    // 切面注解
    @AfterReturning("@annotation(com.example.annotation.Validation)")
    public void afterReturningAdvice(JoinPoint jp) {
        // 强转成 MethodSignature 类型
        MethodSignature signature = (MethodSignature) jp.getSignature();
        // 获取方法上的 Override 注解
        Validation annotation = signature.getMethod().getAnnotation(Validation.class);
        if (annotation != null) {
            System.err.println("进入 @Validation 注解的后置切面，且注解参数为：" + annotation.required());
        }
    }
}
