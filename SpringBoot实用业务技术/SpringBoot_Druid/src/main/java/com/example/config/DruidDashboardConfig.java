package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-07-01 04:40:00
 * @apiNote TODO 以下的Druid 监控、过滤 配置，都可以在 yml 文件中进行配置，但代码配置优先级大于yml
 */
@Configuration
public class DruidDashboardConfig {

	/**
	 * 配置 Druid 监控管理后台的Servlet；
	 * 内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
	 */
	@Bean
	@ConditionalOnClass(DruidDataSource.class)
	public ServletRegistrationBean<?> druidServlet() {
		// 这些参数可以在 http.StatViewServlet 的父类 ResourceServlet 中找到
		Map<String, String> initParams = new HashMap<>();
		// 设置控制台登录的用户名和密码
		initParams.put("loginUsername", "admin");
		initParams.put("loginPassword", "123456");
		// 是否能够重置数据
		initParams.put("resetEnable", "false");
		// IP白名单 (为空表示，所有的都可以访问，多个IP的时候用逗号隔开)
		initParams.put("allow", ""); // 后面参数为空则所有人都能访问，一般会写一个具体的ip或ip段
		// IP黑名单 (存在共同时，deny优先于allow)
		initParams.put("deny", "192.168.10.132");

		// 注册一个servlet，同时表明/druid/* 这个请求会走到这个servlet，而druid内置了这个请求的接收
		ServletRegistrationBean<?> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		bean.setInitParameters(initParams);
		return bean;
	}

	/**
	 * 配置一个web监控的filter
	 */
	@Bean
	@ConditionalOnClass(DruidDataSource.class)
	public FilterRegistrationBean<?> druidFilter() {
		Map<String, String> initParams = new HashMap<>();
		// 这些不进行统计
		initParams.put("exclusions", "*.js,*.css,/druid/*");
		initParams.put("profileEnable", "true");
		initParams.put("principalCookieName", "USER_COOKIE");
		initParams.put("principalSessionName", "");
		initParams.put("aopPatterns", "com.example.service");

		FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new WebStatFilter());
		bean.setInitParameters(initParams);
		bean.addUrlPatterns("/*");
		bean.setInitParameters(initParams);
		return bean;
	}
}
