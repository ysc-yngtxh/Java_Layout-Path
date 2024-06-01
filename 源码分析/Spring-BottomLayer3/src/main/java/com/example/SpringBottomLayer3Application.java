package com.example;

import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
// 开启注解事务管理，等同于xml配置方式的 <tx:annotation-driven/>
@EnableTransactionManagement
public class SpringBottomLayer3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBottomLayer3Application.class, args);
	}
}
