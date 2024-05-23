package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 开启AOP
// proxyTargetClass：默认值为false，表示使用JDK动态代理。将其设置为true，表示不论有没有接口都使用CGLIB动态代理。
// exposeProxy：默认值为false，表示不暴露代理对象。将其设置为true，表示暴露代理对象。即可以对当前类中调用方法进行AOP。
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
// TODO 坑点：
//      Spring 框架中，默认使用的是 JDK代理；如果希望使用的是 CGLIB代理，只需修改属性值 proxyTargetClass = true。
//      SpringBoot 框架中，通过修改属性值 proxyTargetClass 来实现切换代理模式并不生效（始终为CGLIB代理）。
// TODO 原因：
//      SpringBoot 框架中，引入aop的依赖配置（spring-boot-starter-aop）时候，
//      通过 spring.factories 引入了一个自动配置类 AopAutoConfiguration，
//      这个类会自动配置 proxyTargetClass = true，使得代理类通过 CGLIB代理。
//      而我们知道 SpringBoot 的自动配置类，在加载时，它的优先级最低（执行时机是最晚的），
//      因此自动配置类 AopAutoConfiguration 配置会覆盖住 我们手动自定义的配置。
//      所以，不论我们如何修改该属性值 proxyTargetClass，都只会使用 CGLIB代理
// TODO 解决：
//      通过自动配置类 AopAutoConfiguration 源码分析：
//      1、在 resources包下 配置文件中：spring.aop.auto = false 使自动配置类 AopAutoConfiguration 不加载。
//      2、在 resources包下 配置文件中：spring.aop.proxy-target-class = false
//                                   使自动配置类 AopAutoConfiguration 配置 proxyTargetClass = false
@SpringBootApplication
public class SpringBootAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAopApplication.class, args);
    }

    /*
      @EnableAspectJAutoProxy
      --> AspectJAutoProxyRegistrar.class
      --> AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);
      --> return registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry, (Object)null);
      --> AnnotationAwareAspectJAutoProxyCreator.class
      --> extends AspectJAwareAdvisorAutoProxyCreator
      --> extends AbstractAdvisorAutoProxyCreator
      --> extends AbstractAutoProxyCreator
      --> implements SmartInstantiationAwareBeanPostProcessor
      --> extends InstantiationAwareBeanPostProcessor
      --> extends BeanPostProcessor

      一圈下来，你会发现开启 Aop 功能，实际上就是实现 BeanPostProcessor 类的初始化Bean扩展点
    */
}
