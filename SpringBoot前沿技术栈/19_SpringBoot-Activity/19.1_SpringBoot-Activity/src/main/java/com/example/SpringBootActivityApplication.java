package com.example;

import org.activiti.core.common.spring.identity.config.ActivitiSpringIdentityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Activiti 默认集成 Spring Security 进行权限管理，如果不需要权限管理功能，可以通过 exclude 属性排除掉相关的自动配置类
@SpringBootApplication(exclude = {
		ActivitiSpringIdentityAutoConfiguration.class,
		SecurityAutoConfiguration.class
})
public class SpringBootActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivityApplication.class, args);
	}

}
