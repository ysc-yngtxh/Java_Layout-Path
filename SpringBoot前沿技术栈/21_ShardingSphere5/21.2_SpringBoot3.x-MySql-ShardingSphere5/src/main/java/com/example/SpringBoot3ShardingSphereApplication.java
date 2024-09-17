package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class SpringBoot3ShardingSphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3ShardingSphereApplication.class, args);
    }

}
