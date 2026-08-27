package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
/* 开启SpringCloud中的 config服务器，自动提供config服务器相关所有功能。启动时，同步启动Tomcat，默认端口8080。
 * 已把配置中心端口定义为8888，因为config客户端默认查找的config服务器地址是：http://localhost:8888/
 * Config Server的功能是，处理config client的请求，并访问Git，实现配置文件的查询，下载（pull）后，
 * 在本地缓存，并将下载的配置发送给Config Client
 */
@SpringBootApplication
public class ConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class,args);
    }
}
/** 步骤：
 *     1、查看在Eureka服务中是否有Config Server服务
 *     2、打开 http://localhost:8888/test_config/default/master    default运行版本
 *        或者 http://localhost:8888/test_config/dev/master        dev测试版本
 *     3、就可以在控制台上看到从仓库中下载文件到本地的详细地址
 */
