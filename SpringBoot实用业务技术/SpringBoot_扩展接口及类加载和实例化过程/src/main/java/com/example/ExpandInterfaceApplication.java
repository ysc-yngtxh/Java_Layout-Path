package com.example;

import com.example.application.aplicationContextInitializer.MyApplicationContextInitializer;
import com.example.bean.beanPostProcessor.MyBeanPostProcess;
import com.example.executor.Mouse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExpandInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ExpandInterfaceApplication.class);
		// ApplicationContextInitializer接口扩展生效配置
		springApplication.addInitializers(new MyApplicationContextInitializer());
		ConfigurableApplicationContext applicationContext = springApplication.run(args);
		// MyEnvironmentPostProcessor配置环境Environment
		System.out.println("获取环境后处理器数据：" + applicationContext.getEnvironment().getProperty("app.who"));

		// 初始化Mouse
		Mouse mouse = new Mouse();
		MyBeanPostProcess myBeanPostProcess = new MyBeanPostProcess();
	}
}
