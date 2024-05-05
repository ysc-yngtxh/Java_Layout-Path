package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 游家纨绔
 */
@SpringBootApplication   // 开启spring配置
@ServletComponentScan(basePackages = "com.example.servlet")  // 扫描servlet类
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }

}
