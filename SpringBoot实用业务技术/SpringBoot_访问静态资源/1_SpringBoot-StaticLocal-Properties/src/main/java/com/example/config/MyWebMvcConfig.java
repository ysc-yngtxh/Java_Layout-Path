package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        System.out.println("开始进行静态资源映射...");

        // 前两个参数：第一个就是映射访问静态资源的前缀路径，第二个就是自定义的静态资源文件夹名称
        // 注意⚠️：当我们配置多个静态资源执行链时，先配置的资源包优先级最低。
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/img/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链

        registry.addResourceHandler("/script/**")
                .addResourceLocations("classpath:/js/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链

        // registry.addResourceHandler("/resource/prefix/**")
        //         .addResourceLocations("classpath:/resources/")
        //         .setCachePeriod(3600) // 设置缓存时间
        //         .resourceChain(true); // 设置为访问资源执行链
        //
        // registry.addResourceHandler("/resource/prefix/**")
        //         .addResourceLocations("classpath:/META-INF/resources/")
        //         .setCachePeriod(3600) // 设置缓存时间
        //         .resourceChain(true); // 设置为访问资源执行链
        //
        // registry.addResourceHandler("/resource/prefix/**")
        //         .addResourceLocations("classpath:/public/")
        //         .setCachePeriod(3600) // 设置缓存时间
        //         .resourceChain(true); // 设置为访问资源执行链
    }
}