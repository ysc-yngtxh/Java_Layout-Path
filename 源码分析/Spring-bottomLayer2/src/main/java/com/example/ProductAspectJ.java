package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.time.LocalDateTime;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-20 23:57
 * @apiNote TODO
 */
@Aspect
public class ProductAspectJ {

    @Before(value="execution(public void com.example.Product.test())")
    public void myBefore(JoinPoint jp){
        // 获取的是实际的目标类
        Object target = jp.getTarget();
        // 获取的是代理类
        Object aThis = jp.getThis();
        // 获取方法的完整定义
        System.out.println("方法的签名(定义)=" + jp.getSignature());
        System.out.println("方法的名称=" + jp.getSignature().getName());
        // 获取方法的实参
        Object[] args = jp.getArgs();
        for(Object arg : args) {
            System.out.println("参数=" + arg);
        }
        // 就是你切面要执行的功能代码
        System.out.println("前置通知，切面功能：在目标方法之前输出执行时间" + LocalDateTime.now());
    }
}
