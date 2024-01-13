package com.example;

import com.example.excutor.Cat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpandInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpandInterfaceApplication.class, args);
        Cat cat = new Cat();
    }
}
