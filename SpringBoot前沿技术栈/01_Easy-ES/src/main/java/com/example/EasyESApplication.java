package com.example;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("com.example.mapper")
public class EasyESApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyESApplication.class, args);
    }

}
