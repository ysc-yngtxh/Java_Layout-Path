package com.example.aop2;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author 游家纨绔
 * @Aspect：是aspectj框架中的注解。
 *    作用：表示当前类是切面类
 *    切面类：是用来给业务方法增加功能的类，在这个类中有切面的功能代码
 *    位置：在类定义的上面
 *    特点：1、在目标方法之前先执行的
 *         2、不会改变目标方法的执行结果
 *         3、不会影响目标方法的执行
 */
@Aspect
public class Aspect2 {

    /** 后置通知定义方法，方法就是实现切面功能的。
     *  方法的定义要求：
     *     1、公共方法 public
     *     2、方法没有返回值
     *     3、方法名称自定义
     *     4、方法有参数的，推荐是Object，参数名自定义
     *
     *  @AfterReturning:后置通知
     *     属性：1、value  切入点表达式
     *          2、returning  自定义的变量，表示目标方法的返回值的。
     *            自定义变量名必须和通知方法的形参名一样。
     *
     *     位置：在方法定义上面
     *     特点：1、在目标方法之后执行
     *          2、能够获取到目标方法的返回值，可以根据这个返回值做不同的处理功能。Object res = doOther();
     *          3、可以修改这个返回值
     *
     *     后置通知的执行：
     *         Object res = doOther();
     *         myAfterReturning(res);
     *         System.out.println("res"+res)
     */
    @AfterReturning(value="execution(* *..TwoServiceImpl.doOther(..))", returning="res")
    public void myAfterReturning(Object res) {
        // Object res:是目标方法执行后的返回值，根据返回值做你的切面的功能处理
        System.out.println("1==后置通知：在目标方法之后执行的，获取的返回值是：" + res);
        if ("abcd".equals(res)) {
            // 做一些功能
        } else {
            // 做其他功能
        }
    }

	@AfterReturning(value = "execution(* *..TwoServiceImpl.student(..))", returning = "res")
	public void myAfterReturning2(Object res) {
		// Object res:是目标方法执行后的返回值，根据返回值做你的切面的功能处理
		System.out.println("2==后置通知：在目标方法之后执行的，获取的返回值是：" + res);
	}
}
