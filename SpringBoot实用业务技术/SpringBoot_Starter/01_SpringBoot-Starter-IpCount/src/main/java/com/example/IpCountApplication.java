package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IpCountApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpCountApplication.class, args);
    }

    /**
     * 开发自定义的Starter：输出客户端(根据IP区分)的访问次数
     * 1、实现流程：
     *    功能类 - 自动配置类 - 测试工程 - 拦截器完善功能 - 增加配置项
     * 2、自动配置类
     *    resources -> META_INF -> spring.factories       # SpringBoot 2.7废止，SpringBoot 3全面移除
     *    resources -> META_INF -> spring -> org.springframework.boot.autoconfigure.AutoConfiguration.imports
     * 3、测试工程： 开发另一个项目，调用自定义的starter
     * 4、拦截器完善功能
     * 5、增加配置项： 根据我们项目的配置文件决定显示的格式  tools.ip.display=[simple, detailed]
     *
     *   自定义完成Starter后，使用Maven的 install 命令，将自定义starter安装到本地仓库
     *   随后就可以在本地的其他项目中引入相关依赖
     */

}
