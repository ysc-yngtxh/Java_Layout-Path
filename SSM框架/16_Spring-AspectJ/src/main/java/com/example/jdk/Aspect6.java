package com.example.jdk;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Aspect：是aspectj框架中的注解。
 *    作用：表示当前类是切面类
 *    切面类：是用来给业务方法增加功能的类，在这个类中有切面的功能代码
 *    位置：在类定义的上面
 *    特点：1、在目标方法之前先执行的
 *         2、不会改变目标方法的执行结果
 *         3、不会影响目标方法的执行
 */
@Aspect
@Component
public class Aspect6 {

	// 有接口的是jdk代理
	@Before(value = "execution(* *..JdkServiceImpl.like(String, Integer))")
	public void MyText1(JoinPoint jp) {
		Object[] args = jp.getArgs();
		for (Object arg : args) {
			System.out.println("参数：" + arg);
		}
		System.out.println("来，看看小曹的前置通知是怎么做的。");
	}
}
