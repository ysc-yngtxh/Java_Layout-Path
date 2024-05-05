package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dao")
public class InjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InjectApplication.class, args);
	}

	// TODO
	//  @Autowired注入
	//      在容器启动，为对象赋值的时候，遇到@Autowired注解，会用后置处理器机制，来创建属性的实例，
	//      然后再利用反射机制，将实例化好的属性，赋值给对象。
	//  构造器注入
	//      使用构造器能避免注入的依赖是空的情况。因为在bean的生命周期里面先执行的是bean的构造器，然后才给bean里面的属性赋值。
	//  	（一个对象所有依赖的对象先实例化后，才实例化这个对象。没有他们就没有我原则）
	//  setter注入
	//      使用set方法依赖注入时，Spring首先实例化对象，然后才实例化所有依赖的对象。然后通过Jdk反射将属性赋值，完成注入
}
