package com.example.config;

import com.example.annotation.BerConditionalOnClass;
import com.example.entity.Brand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-02 09:20
 * @apiNote TODO
 */
@Configuration
public class CustomAnnotationConfig {

	@Bean
	// 判断是否引入了Tomcat依赖
	@BerConditionalOnClass(name = "org.apache.catalina.startup.Tomcat", lenient = true)
	public Brand tomcatWebServer() {
		return new Brand(4L, "tomcatWebServer", null, null, "小游语录");
	}

	@Bean
	// 判断是否引入了Jetty依赖
	@BerConditionalOnClass(name = "org.eclipse.jetty.server.Server", lenient = true)
	public Brand jettyWebServer() {
		return new Brand(5L, "jettyWebServer", null, null, "小游语录");
	}
}
