package com.example.config;

import com.example.annotation.SelfConditionalOnClass;
import com.example.webServer.JettyWebServer;
import com.example.webServer.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 22:40:00
 * @apiNote TODO 自动配置类
 */
@Configuration
public class WebServerAutoConfiguration {

	@Bean
	// 这里没有引入 SpringBoot 官方依赖，因此没有 @ConditionalOnClass 注解，需要自定义注解使用。
	@SelfConditionalOnClass("org.apache.catalina.startup.Tomcat")
	public TomcatWebServer tomcatWebServer() {
		return new TomcatWebServer();
	}

	@Bean
	// 这里没有引入 SpringBoot 官方依赖，因此没有 @ConditionalOnClass 注解，需要自定义注解使用。
	@SelfConditionalOnClass("org.eclipse.jetty.server.Server")
	public JettyWebServer jettyWebServer() {
		return new JettyWebServer();
	}

}
