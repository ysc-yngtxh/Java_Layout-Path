package com.example.cglib;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

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
public class MyAspect5 {
    // jdk动态代理是由java内部的反射机制来实现的，cglib动态代理底层则是借助asm来实现的。
    // 总的来说，反射机制在生成类的过程中比较高效，而asm在生成类之后的相关执行过程中比较高效
    //       （可以通过将asm生成的类进行缓存，这样解决asm生成类过程低效问题）。
    @Before(value="execution(* *..CglibServiceImpl.like(String,Integer))")
    public void MyText1(JoinPoint jp){
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }
        System.out.println("来，看看喜欢曹玉敏的前置通知是怎么做的。");
    }
}
