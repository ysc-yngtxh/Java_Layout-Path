package com.example.aop;

import com.example.service.BService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-15 21:28
 * @apiNote TODO AService的切面编程类
 */
@Aspect
@Component
public class CAop {

    @Before(value = "execution(* *..AService.testProxy())")
    public void proxy(){
        System.out.println("你正在执行Aop切面逻辑");
    }
}