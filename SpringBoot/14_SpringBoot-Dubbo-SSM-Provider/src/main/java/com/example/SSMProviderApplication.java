package com.example;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
@MapperScan(basePackages = "com.example.dao")
@EnableDubboConfiguration  // 开启dubbo配置
public class SSMProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSMProviderApplication.class, args);
    }

}
