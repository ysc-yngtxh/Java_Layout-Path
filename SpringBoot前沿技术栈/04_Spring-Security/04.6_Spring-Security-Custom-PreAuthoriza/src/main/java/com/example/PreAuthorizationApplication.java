package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
// 启用接口方法的注解权限使用。启用@EnableMethodSecurity注解后，prePostEnabled属性值默认为 true。
// prePostEnabled = true：启用 @PreAuthorize 和 @PostAuthorize 注解。
// securedEnabled = true：启用 @Secured 注解。
// jsr250Enabled = true：启用 JSR-250 注解（如 @RolesAllowed）。
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class PreAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreAuthorizationApplication.class, args);
	}
}
