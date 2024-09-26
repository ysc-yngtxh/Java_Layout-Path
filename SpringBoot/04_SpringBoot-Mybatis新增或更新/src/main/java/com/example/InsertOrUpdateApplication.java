package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
// 开启spring配置
@SpringBootApplication
// 开启扫描Mpper接口的包以及子目录.就不用在StudentMapper类上加@Mapper注解
@MapperScan(basePackages = "com.example.mapper")
public class InsertOrUpdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsertOrUpdateApplication.class, args);
    }

}


/**
 * 比较前一个模块，此模块可以避免在dao包下的Mapper类较多时，每一个加上@Mapper注解
 * 因为，统一在Application类下开启了一个类似扫描器的注解@MapperScan
 */
