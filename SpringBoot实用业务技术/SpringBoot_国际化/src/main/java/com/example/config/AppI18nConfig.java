package com.example.config;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2023-03-09 23:00
 */
@Configuration
public class AppI18nConfig implements InitializingBean {

	/**
	 * InitializingBean是Spring提供的拓展性接口，InitializingBean接口为bean提供了属性初始化后的处理方法，
	 * 它只有一个afterPropertiesSet方法，凡是继承该接口的类，在bean的属性初始化后都会执行该方法。
	 * 执行顺序优先级：构造方法、注解postConstruct，实现InitializingBean方法afterPropertiesSet，bean初始化init方法
	 * 构造方法 > postConstruct > afterPropertiesSet > init11方法。
	 */

	private static AppI18nConfig appI18nConfig;
	@Value("${xxl.job.i18n}")
	private String i18n;
	@Getter
	@Value("${spring.messages.basename}")
	private String baseName;

	public AppI18nConfig() {
		System.out.println("构造方法执行");
	}

	public static AppI18nConfig getAppConfig() {
		return appI18nConfig;
	}

	@PostConstruct
	public void methods() {
		System.out.println("postConstruct注解方法执行");
	}

	@Override
	public void afterPropertiesSet() {
		appI18nConfig = this;
		System.out.println("接口initializingBean的实现方法执行");
	}

	public void init11() {
		System.out.println("我是init方法执行...");
	}

	// @Bean 注解的 initMethod 属性用于指定 Bean 初始化完成后要调用的方法。
	@Bean(initMethod = "init11")
	public AppI18nConfig test() {
		return new AppI18nConfig();
	}

	public String getI18n() {
		if (!Arrays.asList("zh_CN", "zh_TW", "en_US").contains(i18n)) {
			return "zh_CN";
		}
		return i18n;
	}
}
