package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
public class CanalEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalEsApplication.class, args);
    }

}
