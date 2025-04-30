package com.example.config;

import com.example.interceptor.BucketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 令牌桶拦截器 添加拦截器并选择拦截路径
        registry.addInterceptor(bucketInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public BucketInterceptor bucketInterceptor() {
        return new BucketInterceptor();
    }
}
