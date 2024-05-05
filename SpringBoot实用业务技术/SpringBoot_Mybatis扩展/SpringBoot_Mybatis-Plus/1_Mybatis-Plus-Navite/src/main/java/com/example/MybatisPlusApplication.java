package com.example;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        // TODO 使用Mybatis-plus设置 yml文件数据安全，在没有我们的密钥情况下可以避免我们开发项目的配置文件信息泄露
        // 生成十六位随机AES密钥，妥善保管
        String randomKey= AES.generateRandomKey();
        System.out.println(randomKey); // 163b68e7477292c0
        // 使用随机密钥加密需要加密的数据，列如数据库url,username,password
        String url = "jdbc:mysql://localhost:3306/springdb?serverTimezone=UTC&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "131474";
        String aesUrl = AES.encrypt(url, randomKey);
        String aesUsername = AES.encrypt(username, randomKey);
        String aesPassword = AES.encrypt(password, randomKey);
        System.out.println("url: " + aesUrl);
        System.out.println("username: " + aesUsername);
        System.out.println("password: " + aesPassword);
        // TODO yml文件进行加密配置之后，我们想正常启动，要在加密数据前加上前缀 mpw:
        //  情况一：在 Idea 上启动项目的话设置 Program arguments = --mpw.key=163b68e7477292c0
        //  情况二：项目为jar包，并利用命令行启动 java -jar 项目名.jar --mpw.key=163b68e7477292c0

        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
