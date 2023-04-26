package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.example.filter")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
