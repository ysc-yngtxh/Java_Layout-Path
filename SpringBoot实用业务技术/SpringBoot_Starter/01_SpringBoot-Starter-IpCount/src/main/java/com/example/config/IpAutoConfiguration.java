package com.example.config;

import com.example.enums.DisplayModeEnum;
import com.example.interceptor.IpCountInterceptor;
import com.example.service.IpCountService;
import com.example.service.impl.IpCountServiceImpl;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-20 19:38
 * @apiNote TODO
 */
@Data
@Configuration
@ConfigurationProperties("tools.ip")
public class IpAutoConfiguration implements WebMvcConfigurer {

    // 轮子默认模式为 simple
    private String display = DisplayModeEnum.SIMPLE.getMode();

    @Bean
    public IpCountService ipCountService() {
        return new IpCountServiceImpl();
    }

    @Bean
    public IpCountInterceptor ipCountInterceptor() {
        return new IpCountInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipCountInterceptor()).addPathPatterns("/**");
    }
}
