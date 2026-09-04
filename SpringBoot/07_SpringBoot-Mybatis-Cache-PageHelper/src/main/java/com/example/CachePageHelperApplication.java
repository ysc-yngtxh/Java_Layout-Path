package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 开启扫描 Mapper接口 的包以及子目录，就不用在 Mapper接口 上加 @Mapper注解
@MapperScan(basePackages = "com.example.mapper")
public class CachePageHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(CachePageHelperApplication.class, args);
    }

}
