package com.example;

import com.example.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
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
public class SpringBottomLayer2Application {

    public static void main(String[] args) {
        // 启动 Spring 容器
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBottomLayer2Application.class, args);

        // Spring 的 Aop 底层原理
        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        // 1、由于 productService Bean具有 Aop 切面操作，可以在控制台上看到 productService 对象值是代理对象
        //    如果将 Aop 切面代码注释掉，那么在控制台显示的 productService 对象值会是确切对应的原始对象
        // 2、问题：对于原始 productService Bean对象来说，注入的属性 itemService 是存在值的。
        //         如果 productService 对象值是Aop代理对象，该 ProductService 中注入的属性 itemService 值为 null。
        // 3、解答：对于代理对象来说，我实际关注点应该是代理的目标对象方法执行之前和执行之后，需要增强的功能，而并非目标对想象中注入的属性。
        //         况且，
        productService.test();

        // 为什么使用 Aop 切面操作后，就得使用代理对象来取代原始对象？
        // 因为动态代理可以在目标类源代码不改变的情况下，增加功能。

        // Aop代理对象 productService($CGLIB) 中有一个属性 target = ProductServiceImpl@7565
        // 当 Aop 切面时，无论是前置通知还是后置通知，都能在代理对象中得到功能增强。
        // 并可以控制属性 target 决定是否要执行目标对象方法。

        // Aop实际上是实现了 BeanPostProcessor接口，因此
        // 推断构造方法 -> 普通对象 -> 依赖注入 -> 初始化 afterPropertiesSet -> 初始化后Aop -> 代理对象 -> 放入 Map<beanName, bean对象>
    }

}
