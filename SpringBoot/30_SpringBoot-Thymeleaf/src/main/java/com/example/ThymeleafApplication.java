package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafApplication.class, args);
    }
}
/**
 * thymeleaf是实现前后端分离的一种工具，选用的是HTML文件
 *     想要在HTML中实时的添加语句并且能在不重启springboot服务的情况下，完成浏览器页面的实时更新数据
 *     1、在配置文件中关闭thymeleaf模板引擎的缓存
 *         spring.thymeleaf.cache=false
 *     2、在Application的Configuration中选用更新缓存的选项
 */
