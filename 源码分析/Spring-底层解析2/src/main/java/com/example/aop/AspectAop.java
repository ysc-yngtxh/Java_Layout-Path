package com.example.aop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-20 23:00:00
 * @apiNote TODO
 */
@Aspect
@Component
public class AspectAop {

	@Before(value = "execution(* *..ProductServiceImpl.test())")
	public void myBefore(JoinPoint jp) {
		// 获取的是实际的目标类
		System.out.println("Aop底层实现为动态代理，因此代理的目标类为：" + jp.getTarget().getClass().getName());
		// 获取的是代理类
		System.out.println("Aop底层实现为动态代理，因此代理类为：" + jp.getThis().getClass().getName());

		// 获取方法的完整定义
		System.out.println("方法的签名(定义) = " + jp.getSignature());
		System.out.println("方法的名称 = " + jp.getSignature().getName());
		// 获取方法的实参
		Object[] args = jp.getArgs();
		for (Object arg : args) {
			System.out.println("参数 = " + arg);
		}
		// 就是你切面要执行的功能代码
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println("前置通知，切面功能：在目标方法之前输出执行时间 " + LocalDateTime.now().format(formatter));
	}

}
