package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// 开启注解事务管理，等同于xml配置方式的 <tx:annotation-driven/>
@EnableTransactionManagement
public class TransactionManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionManagerApplication.class, args);
    }


    // Spring中的事务管理是基于AOP（面向切面编程）实现的，它通过动态代理来拦截方法调用并管理事务。
    // 而在Spring中 TransactionInterceptor 和 PlatformTransactionManager 这两个类是整个事务模块的核心，
    // TransactionInterceptor 负责拦截方法执行，进行判断是否需要提交或者回滚事务。
    // PlatformTransactionManager 是Spring 中的事务管理接口，真正定义了事务如何回滚和提交。
    // 当一个带有 @Transactional 注解的方法被调用时，TransactionInterceptor（事务拦截器）会在方法调用前后执行，
    // 负责事务的开启、提交或回滚。这发生在Spring的代理对象中，代理对象在运行时根据注解生成，以透明地处理事务逻辑。


    // 如果在一个类的内部方法调用另一个需要事务管理的方法，而这个调用不是通过代理对象完成的，那么事务可能就不会生效。
    // 因此，如果插入操作是通过内部方法直接调用而不是通过外部接口（经过代理）调用的，事务可能就不会被正确管理，导致插入失败但ID自增。


}
