package com.example;

import com.example.listener.CustomEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

// 启动类增加此注解，开启异步支持。
@EnableAsync
@SpringBootApplication
public class SpringBootEventApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootEventApplication.class);
        springApplication.addListeners(new CustomEventListener());
        springApplication.run(args);
    }

}
