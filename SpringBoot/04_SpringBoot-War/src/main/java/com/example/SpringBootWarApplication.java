package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
public class SpringBootWarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWarApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 参数为当前springboot启动类
		// 构造新资源
		return builder.sources(SpringBootWarApplication.class);
	}
}
/* SpringBoot项目 打 war包 部署到 Tomcat 流程
 *   1、确保项目能正常执行
 *   2、在 pom文件 中修改打包方式为 war
 *   3、还要在启动类 Application 中继承 SpringBootServletInitializer，重写其 configure方法
 *   4、然后在 Maven 中进行打包操作 Lifecycle --> clean --> package
 *   5、打好的包在项目 target目录下 -- SpringBootWar.war
 *   6、然后将其 war包 放在 Tomcat 文件的 webapps文件夹下
 *   7、再去运行 Tomcat 服务器的 bin目录下的 startup.bat。（可以发现命令行中出现了SpringBoot的Logo）
 *   8、最后在浏览器地址栏上输入地址名，注意：上下文根有一个SpringBootWar一定要加上
 *   9、关闭服务不要直接关闭窗口，要运行 bin目录下的 shutdown.bat
 */
