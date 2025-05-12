package com.example.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author 游家纨绔
 * @dateTime 2023-04-30 09:00
 * @apiNote TODO 切面编程类
 */
@Slf4j
@Aspect
@Component
public class DefinitionAop {

    HttpServletRequest request = null;

    @Around(value = "execution(* *..RequestController.*(..))")
    public Object test(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Arrays.stream(args).forEach(item -> {
            if (item instanceof HttpServletRequest){
                request = (HttpServletRequest) item;
            }
            System.out.println("环绕通知" + item);
        });
        if (Objects.isNull(request) || request.getMethod().equalsIgnoreCase("get")){
            return jp.proceed();
        }
        Object proceed = jp.proceed();
        String userName1 = request.getParameter("userName");
        System.out.println("环绕通知2"+userName1);
        return proceed;
    }
}
