package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        System.out.println("开始进行静态资源映射...");

        // 方法里两个参数：第一个就是映射访问静态资源的前缀路径，第二个就是自定义的静态资源文件夹名称
        registry.addResourceHandler("/resource/prefix/**")
                .addResourceLocations("classpath:/templates/")
                .setCachePeriod(3600)
                .resourceChain(true);

        registry.addResourceHandler("/resource/prefix/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .setCachePeriod(3600)
                .resourceChain(true);

        registry.addResourceHandler("/resource/prefix/**")
                .addResourceLocations("classpath:/img/")
                .setCachePeriod(3600)
                .resourceChain(true);
    }
}