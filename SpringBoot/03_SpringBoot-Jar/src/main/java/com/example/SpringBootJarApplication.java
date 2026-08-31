package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
public class SpringBootJarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJarApplication.class, args);
	}
}
/*
 * SpringBoot项目默认打包方式为 jar包，以下为部署到 Tomcat 服务器流程：
 *   1、保证项目能正常执行
 *   2、在配置文件中，可以去修改访问地址的端口号
 *   3、清理，打包 --> Maven --> Lifecycle --> clean --> package
 *   4、将其 jar包 放置任意目录下，在目录下打开终端命令行
 *   5、在命令行中输入：java -jar jar包名
 *   6、回车可以看见 SpringBoot 的大Logo
 *   7、最后在浏览器中输入地址名，注意：端口号要写上你修改后的端口号
 *
 * 比较 war包 与 jar包 的区别：
 *      1、war包 属于 Web工程，需要交给某个类似容器的功能处理，所以需要放置在 Tomcat 中才能运行
 *         jar包 就不需要什么其他功能辅助处理，因为 jar包 有内嵌的Tomcat，可以自己运行
 *      2、war包 部署到 Tomcat 上，他的端口号和上下文根与本地的 Tomcat 有关，
 *         jar包 的端口号和上下文根则只与自己的配置文件相关
 */
