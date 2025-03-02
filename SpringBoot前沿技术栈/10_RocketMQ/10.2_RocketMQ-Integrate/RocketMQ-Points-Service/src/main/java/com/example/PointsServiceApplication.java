package com.example;

import com.example.mq.annotation.EnableRocketMQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRocketMQ
public class PointsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointsServiceApplication.class, args);
    }

}
