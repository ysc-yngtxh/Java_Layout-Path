package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
public class WarApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WarApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 参数为当前springboot启动类
        // 构造新资源
        return builder.sources(WarApplication.class);
    }
}
/*
   SpringBoot打war包部署到Tomcat流程
     1、确保项目能正常执行
     2、在pom文件中修改打包方式为war
     3、还要在Application类中继承 SpringBootServletInitializer，重写其configure方法
     4、然后在Maven中进行打包操作 Lifecycle --> clean --> package
     5、打好的包在项目 target下--SpringBootWar.war
     6、然后将其war包放在Tomcat文件的webapps文件夹下
     7、再去运行bin目录下的startup.bat。可以发现命令行中出现了SpringBoot的Logo
     8、最后在浏览器地址栏上输入地址名，注意：上下文根有一个SpringBootWar一定要加上
     9、关闭服务不要直接关闭窗口，要运行bin目录下的shutdown.bat-
 */