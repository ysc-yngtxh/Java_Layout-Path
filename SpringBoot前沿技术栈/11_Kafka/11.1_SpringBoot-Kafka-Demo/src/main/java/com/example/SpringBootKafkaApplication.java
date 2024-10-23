package com.example;

import com.example.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaApplication.class, args);
    }

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        kafkaProducerService.sendMessage("my-topic", "Hello, Kafka!");
    }
}
