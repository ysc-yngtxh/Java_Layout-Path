package com.example.自定义注解.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/12/1 11:59
 */
@Aspect
// @Component
public class LoginAop {

    /**
     * 这里有个问题啊，你会发现切面Controller类中的方法没生效(切面Service层就不会有这问题)：
     *     1、Spring与SpringMVC是2个不同的父子容器， @Aspect如果被spring容器加载的话，
     *       而@Controller注解的这些类的实例化以及注入却是由SpringMVC来完成。
     *     2、@Aspect如果被spring容器加载的时候，可能Spring MVC容器还未初始化，Controller类还未初始化，所以无法正常植入.
     *
     *  解决方案：
     *     首先去掉@Component注解，然后把  aop:aspectj-autoproxy  移入springmvc配置文件中，并定义bean
     */

    @Around("@annotation(com.example.自定义注解.annotation.LoginRequired)")
    public Object loginAopTest(ProceedingJoinPoint point) throws Throwable{
        MethodSignature name = (MethodSignature) point.getSignature();
        Method method = name.getMethod();
        System.out.println(method.toString());
        String name1 = point.getTarget().getClass().getName();
        System.out.println(name1);
        return point.proceed();
    }

    @Before(value="execution(* *..loginController.login1())")
    public void loginAopTest1(JoinPoint point) throws Throwable{
        MethodSignature name = (MethodSignature) point.getSignature();
        Method method = name.getMethod();
        System.out.println(method.toString());
        String name1 = point.getTarget().getClass().getName();
        System.out.println(name1);
    }
}
