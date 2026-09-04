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

	// 如果在一个类的内部方法调用另一个需要事务管理的方法，而这个调用不是通过代理对象完成的，那么事务可能就不会生效。
	// 因此，如果插入操作是通过内部方法直接调用而不是通过外部接口（经过代理）调用的，事务可能就不会被正确管理，导致插入失败但ID自增。
}
