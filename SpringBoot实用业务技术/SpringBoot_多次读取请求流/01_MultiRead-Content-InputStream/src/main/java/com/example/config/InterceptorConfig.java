package com.example.config;

import com.example.interceptor.DefinitionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-01 12:00
 * @apiNote TODO 拦截器配置类
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DefinitionInterceptor()).addPathPatterns("/*");
	}
}
