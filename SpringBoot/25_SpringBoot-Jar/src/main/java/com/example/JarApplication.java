package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
public class JarApplication {

    public static void main(String[] args) {
        SpringApplication.run(JarApplication.class, args);
    }
}
/*
 * SpringBoot打jar包部署到Tomcat流程
 *   1、保证项目能正常执行
 *   2、在配置文件中，可以去修改访问地址的端口号
 *   3、清理，打包 --> Maven --> Lifecycle --> clean --> package
 *   4、将其jar包放置任意目录下，在目录下输入cmd打开命令行
 *   5、在命令行中输入：java -jar jar包名
 *   6、回车可以看见springboot的大Logo
 *   7、最后在浏览器中输入地址名，注意：端口号要写上你修改后的端口号
 *
 * 比较war包与jar包的区别：
 *      1、war包属于web工程，需要交给某个类似容器的功能处理，所以需要放置在Tomcat中才能运行
 *         jar包就不需要什么其他功能辅助处理，因为jar包有内嵌的Tomcat,可以自己运行
 *      2、war包部署到Tomcat上，他的端口号和上下文根与本地的Tomcat有关，
 *         jar包的端口号和上下文根则只与自己的配置文件相关
 */
