package com.example.config;

import com.example.enums.DisplayModeEnum;
import com.example.interceptor.IpCountInterceptor;
import com.example.service.IpCountService;
import com.example.service.impl.IpCountServiceImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-20 19:40:00
 * @apiNote TODO
 */
@Data
// 这里你肯定会很奇怪，为什么我使用了Spring的SPI机制将当前类注入到了Spring容器中，却还是需要加上@Configuration注解呢？
// 原因就在于：不加上@Configuration，那么这个类就只是一个普通的Bean，而不是一个配置Bean，就无法将@Bean标注的类注入Spring
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
		// registry.addInterceptor(new IpCountInterceptor()).addPathPatterns("/**");
		// ⚠️：正常应用拦截器逻辑类是无需注入到Spring容器，但是 IpCountInterceptor 类中使用了 @Autowired 注解，所以需要将其注入到Spring容器中。
		registry.addInterceptor(ipCountInterceptor()).addPathPatterns("/**");
	}
}
