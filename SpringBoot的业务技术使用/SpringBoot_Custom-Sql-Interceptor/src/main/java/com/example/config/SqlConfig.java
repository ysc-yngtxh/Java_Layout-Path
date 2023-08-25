package com.example.config;

import com.example.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 15:08
 * @apiNote TODO
 */
@Configuration
public class SqlConfig implements WebMvcConfigurer {
    String[] addPath = {
            "/tbUser/**"
    };
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns(addPath)
                .excludePathPatterns();
    }
}
