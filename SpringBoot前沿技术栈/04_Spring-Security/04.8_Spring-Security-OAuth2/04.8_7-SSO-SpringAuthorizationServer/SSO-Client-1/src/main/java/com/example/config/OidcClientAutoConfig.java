package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OidcClientAutoConfig {

	@Bean
	public SecurityFilterChain authorizationClientSecurityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						                       .anyRequest().authenticated()
				                      )
				.oauth2Login(Customizer.withDefaults())

				// TODO 以下为单点登出配置
				// 禁用 csrf 保护，则不会向用户显示注销确认页面，而是直接执行注销
				.csrf(AbstractHttpConfigurer::disable)
				// 开启自定义登出配置信息（客户端应用退出登录后，调用服务端的退出登录接口）
				.logout(logout ->
						        logout.logoutSuccessUrl("http://localhost:9090/logout")
				       );

		return http.build();
	}
}
