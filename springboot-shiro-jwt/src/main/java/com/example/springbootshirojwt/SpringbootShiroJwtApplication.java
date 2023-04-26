package com.example.springbootshirojwt;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springbootshirojwt.mapper")
public class SpringbootShiroJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroJwtApplication.class, args);
    }

}
