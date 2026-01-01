package com.example.aop3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Aspect 是aspectj框架中的注解。<p>
 *    作用：表示当前类是切面类。<p>
 *    切面类：是用来给业务方法增加功能的类，在这个类中有切面的功能代码。<p>
 *    位置：在类定义的上面。<p>
 *    特点：1、在目标方法之前先执行的
 *         2、不会改变目标方法的执行结果
 *         3、不会影响目标方法的执行
 */
@Aspect
public class Aspect3 {

    /** 环绕通知方法的定义格式<p>
     *     1、public
     *     2、必须有一个返回值，推荐使用Object
     *     3、方法名称自定义
     *     4、方法有参数，固定的参数  ProceedingJoinPoint
     *
     *  @Around: 环绕通知 <p>
     *     属性：value  切入点表达式 <p>
     *     位置：在方法的定义什么 <p>
     *     特点：
     *         1、他是功能最强的通知
     *         2、在目标方法的前和后都能增强功能
     *         3、控制目标方法是否被调用执行
     *         4、修改原来的目标方法的执行结果。影响最后的调用结果
     * <p>
     *     环绕通知，等同于jdk动态代理的，InvocationHandler接口
     * <p>
     *     参数：ProceedingJoinPoint  就等同于 Method
     *          作用：执行目标方法的
     *     返回值：就是目标方法的执行结果，可以被修改
     * <p>
     *     环绕通知：经常做事务，在目标方法之前开启事务，执行目标方法，在目标方法之后提交事务 
     */
    @Around(value="execution(* *..ThreeServiceImpl.First(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取的是实际的目标类
        System.out.println("Aop底层实现为动态代理，因此代理的目标类为：" + pjp.getTarget().getClass().getName());
        // 获取的是代理类
        System.out.println("Aop底层实现为动态代理，因此代理类为：" + pjp.getThis().getClass().getName());

		String name = null;
		// 获取第一个参数值
		Object[] args = pjp.getArgs();
		if (args != null && args.length > 1) {
			name = (String) args[0];
		}

		// 实现环绕通知
		Object result = null;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println("环绕通知，在目标方法之前，输出时间：" + LocalDateTime.now().format(dateTimeFormatter));

		// 1、目标方法调用
		if ("小敏我喜欢你".equals(name)) {
			result = pjp.proceed();
		}
		System.out.println("环绕通知：在目标方法之后，提交事务");

		// 2、在目标方法的前或者后加入功能（修改目标方法的执行结果，影响方法最后的调用结果）
		if (result != null) {
			result = "Hello AspectJ AOP";
		}

		// 返回目标方法的执行结果
		return result;
	}
}
