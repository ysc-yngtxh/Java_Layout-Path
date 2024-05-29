package com.example;

import com.example.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
