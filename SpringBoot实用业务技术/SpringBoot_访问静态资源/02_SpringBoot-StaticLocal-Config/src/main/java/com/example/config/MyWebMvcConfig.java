package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        System.out.println("开始进行Bean方式配置静态资源映射，yml文件不进行配置。。。");

        // 前两个参数：第一个就是映射访问静态资源的前缀路径，第二个就是自定义的静态资源文件夹名称
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/img/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链

        registry.addResourceHandler("/script/**")
                .addResourceLocations("classpath:/js/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链

        // TODO 注意⚠️：当我们使用Bean方式配置静态资源前缀路径为/**时，会将官方默认设置的四种静态资源包覆盖掉。
        //  相当于在配置文件中 spring.web.resources.static-locations = classpath:/templates
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链

        // TODO 当出现多个对同一前缀路径做设置的资源包，那么执行链上的最后一个会覆盖掉前面设置的资源包。
        //  也就是 /resource/prefix/** 路径下，只有 classpath:/public包资源才能被访问到
        registry.addResourceHandler("/resource/prefix/**")
                .addResourceLocations("classpath:/resources/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链
        registry.addResourceHandler("/resource/prefix/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链
        registry.addResourceHandler("/resource/prefix/**")
                .addResourceLocations("classpath:/public/")
                .setCachePeriod(3600) // 设置缓存时间
                .resourceChain(true); // 设置为访问资源执行链
    }
}