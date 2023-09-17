package com.example.springbootshirojwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    // 这个过滤器是为了在解析域名后阻拦Ajax请求的
    @Bean
    public CorsFilter corsFilter(){
        // 1、添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        // (1)、允许的域，不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://localhost:8081/");//这里如果不想写死的话，可以写到配置文件中
        // (2)、是否发送cookie信息
        config.setAllowCredentials(true);
        // (3)、允许的发送方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // (4)、允许的头部信息
        config.addAllowedHeader("*");
        // (5)、有效时长
        config.setMaxAge(3600L);//一个小时

        // 2、添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);

        // 3、返回新的CorsFilter
        return new CorsFilter(configSource);
    }
}
