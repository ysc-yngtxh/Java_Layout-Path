package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-26 21:40:00
 * @apiNote TODO
 */
@Aspect
@Component
public class MethodProxy {

	@Before(value = "execution(* *..findBrandById(*))")
	public void before(JoinPoint joinPoint) {
		System.err.println("进入切面逻辑～");
	}
}
