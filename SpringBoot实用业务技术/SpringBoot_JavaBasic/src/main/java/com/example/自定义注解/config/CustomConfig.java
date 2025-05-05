package com.example.自定义注解.config;

import com.example.自定义注解.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-05 16:21:20
 */
@Configuration
public class CustomConfig implements WebMvcConfigurer {

	// 这里可以添加一些自定义的配置，比如拦截器、视图解析器等
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
	}
}
