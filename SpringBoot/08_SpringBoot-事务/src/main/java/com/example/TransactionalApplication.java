package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
@MapperScan(basePackages = "com.example.dao")
public class TransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class, args);
    }

    // TODO 通过 spring.factories 自动装配 TransactionAutoConfiguration 类，来自动去加载对事务管理的配置。
    //      因此，现版本 SpringBoot 不需要添加 @EnableTransactionManager，就能使用事务
}
