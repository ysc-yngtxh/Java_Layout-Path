package com.example.canalelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.canalelasticsearch.mapper")
public class CanalEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalEsApplication.class, args);
    }

}
