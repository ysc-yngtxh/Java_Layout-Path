package com.example.config;

import com.example.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 游家纨绔
 * @dateTime 2023-07-11 07:50:00
 * @apiNote TODO 注册拦截器
 */
@Configuration
public class RegistryInterceptor implements WebMvcConfigurer {

	String[] addPath = {
			"/user/**"
	};

	String[] excludePath = {
			"/user/login"
	};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserInterceptor())
		        .addPathPatterns(addPath)
		        .excludePathPatterns(excludePath);
	}
}
