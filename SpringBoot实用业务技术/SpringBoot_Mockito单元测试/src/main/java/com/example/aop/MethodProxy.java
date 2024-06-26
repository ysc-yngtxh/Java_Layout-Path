package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-26 21:39
 * @apiNote TODO
 */
@Aspect
public class MethodProxy {

    // @Before(value = "execution(* *..service.CategoryService.findBrandById(*))")
    // public void before(JoinPoint joinPoint) {
    //
    // }
}
