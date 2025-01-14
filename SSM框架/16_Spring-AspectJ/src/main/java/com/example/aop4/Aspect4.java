package com.example.aop4;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

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
public class Aspect4 {
    /**
     * @Pointcut:定义和管理切入点，如果你的项目中有多个切入点表达式是重复的，可以使用@Pointcut
     *      属性：value 切入点表达式
     *      位置：在自定义的方法上面
     *      特点：当使用@Pointcut定义在一个方法的上面，此时这个方法的名称就是切入点表达式的别名。
     *           其他的通知中，value属性就可以使用这个方法名称，代替切入点表达式了。
     */
    @Pointcut(value="execution(* *..FourServiceImpl.like(..))")
    private void myPoint(){
        // 无需代码
    }

    @Before(value="execution(* *..FourServiceImpl.like(String, Integer))") // 这里使用了JoinPoint jp需要参数类型，故而不使用mypt()
    public void MyText1(JoinPoint jp){
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }
        System.out.println("来，看看小曹的前置通知是怎么做的。");
    }


    @AfterReturning(value="myPoint()", returning="res")
    public void MyText2(Object res){
        System.out.println("嘚，我们小曹的后置通知，铛铛铛！" + res);
        if("曹玉敏，我喜欢你!".equals(res)) {
            System.out.println("期待再次相逢");
        } else {
            System.out.println("我好想你啊！");
        }
    }

    @Around(value="myPoint()")
    public Object MyText3(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("然后我们再来看一下小曹的环绕通知，在目标方法执行之前:" + new Date());

        String name = null;
        Object[] args = pjp.getArgs();
        if(args!=null && args.length>1){
            name = (String)args[0];
        }
        Object result = null;
        if("小曹，我like你!".equals(name)){
            result = pjp.proceed();
        }

        System.out.println("我们环绕通知，在目标方法执行之后，提交事务");

        // 其实这里是可以做修改返回值的，但是为了能使后置通知正常执行，故没做修改。
        if(result != null){
            result = "小曹，我like你!";
        }
        return result;
    }

    // 在我自己做的总结概述中，发现当我们环绕通知没有返回值时，会影响到后置通知的 returning="res" 的参数值(为null)
    // 说明，在执行环绕通知时，把目标方法的返回值'挪用'到自己身上了。所以，后置通知上目标方法的返回值要看环绕通知的返回值的'脸色'
}
