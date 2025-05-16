package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		System.out.println("开始进行Bean方式配置静态资源映射，yml文件参与配置。。。");

		// TODO 当应用启动时，Spring Boot会先加载YML文件中的配置，然后再加载@Configuration注解标识的配置类中的配置。
		//  这意味着，如果存在相同的配置项，@Configuration中的配置会覆盖YML文件中的配置。
		registry.addResourceHandler("/resource/prefix/**")
		        .addResourceLocations("classpath:/resources/")
		        .setCachePeriod(3600) // 设置缓存时间
		        .resourceChain(true); // 设置为访问资源执行链

		// TODO 当出现多个对同一前缀路径做设置的资源包，那么执行链上的最后一个会覆盖掉前面设置的资源包。
		//  也就是 /resource/prefix/** 路径下，只有 classpath:/templates包资源才能被访问到
		registry.addResourceHandler("/resource/prefix/**")
		        .addResourceLocations("classpath:/META-INF/resources/")
		        .setCachePeriod(3600) // 设置缓存时间
		        .resourceChain(true); // 设置为访问资源执行链
		registry.addResourceHandler("/resource/prefix/**")
		        .addResourceLocations("classpath:/public/")
		        .setCachePeriod(3600) // 设置缓存时间
		        .resourceChain(true); // 设置为访问资源执行链
		registry.addResourceHandler("/resource/prefix/**")
		        .addResourceLocations("classpath:/templates/")
		        .setCachePeriod(3600) // 设置缓存时间
		        .resourceChain(true); // 设置为访问资源执行链
	}
}
