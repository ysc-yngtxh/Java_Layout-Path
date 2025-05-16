package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
public class TransactionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionalApplication.class, args);
	}

	// TODO 要启用事务管理，通常需要在应用的主配置类上添加 @EnableTransactionManagement 注解。
	//      但在 SpringBoot 2.x 及以上版本中，这一注解往往是可选的，因为 SpringBoot 会自动检测并启用事务管理。
	//      通过 spring.factories 自动装配 TransactionAutoConfiguration 类，来自动去加载对事务管理的配置。
	//      因此，现版本 SpringBoot 不需要添加 @EnableTransactionManager，就能使用事务
}
