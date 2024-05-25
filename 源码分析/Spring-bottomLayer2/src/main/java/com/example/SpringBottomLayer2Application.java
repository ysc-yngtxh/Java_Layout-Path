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

        //  TODO 由于 productService Bean具有 Aop 切面操作，可以打断点在控制台上看到 productService 对象值是代理对象
        //       如果将 Aop 切面代码注释掉，那么在控制台显示的 productService 对象值会是确切对应的原始对象
        // 问题1：对于原始 productService Bean对象来说，注入的属性 itemService 是存在值的。
        //       如果 productService 对象值是代理对象，该 ProductService 中注入的属性 itemService 值为 null。
        // 解答1：代理对象并没有对任何属性进行依赖注入。因此这里 代理对象 中属性 itemService 为 null
        ProductService productService = applicationContext.getBean("productService", ProductService.class);

        // 问题2：既然代理对象属性值 itemService 为null，那通过代理对象去执行方法，为什么在方法中打印出 itemService 属性却是有值的？
        // 解答2：因为代理对象可以看做继承目标对象，并且存在一个属性 target = ProductServiceImpl@7565(原始对象)
        //           public class proxy extends ProductServiceImpl {
        //               private ProductServiceImpl target;
        //               public void doSomething() {
        //                   // 在这里执行切面前置逻辑......
        //                   target.doSomething(); // 这里执行目标对象的方法
        //                   // 在这里执行切面后置逻辑......
        //               }
        //           }
        //       所以代理对象执行方法，是通过 target 属性可以访问到原始对象，从间接访问到原始对象中属性 itemService
        productService.test();

        // Aop实际上是实现了 BeanPostProcessor接口
        // 推断构造方法 -> 普通对象 -> 依赖注入 -> 初始化 afterPropertiesSet -> 初始化后Aop -> 代理对象 -> 放入 Map<beanName, bean对象>
    }

}
