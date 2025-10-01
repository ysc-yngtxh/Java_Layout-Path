package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-26 21:40:00
 * @apiNote TODO
 */
@Aspect
@Component
public class MethodProxy {

	@Before(value = "execution(* *..findAllById(*))")
	public void before(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String className = signature.getMethod().getDeclaringClass().getSimpleName();
		String methodName = signature.getMethod().getName();
		System.err.printf(
				"""
				进入类名为 %s 里的 %s() 方法的切面逻辑～
				""",
				className,
				methodName
		);
	}
}
