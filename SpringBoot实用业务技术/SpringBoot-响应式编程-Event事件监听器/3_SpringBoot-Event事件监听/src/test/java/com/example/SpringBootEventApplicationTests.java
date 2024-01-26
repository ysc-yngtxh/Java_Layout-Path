package com.example;

import com.example.event.TestEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
class SpringBootEventApplicationTests {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void contextLoads() {
        for (int i = 0; i < 5; i++) {
            applicationEventPublisher.publishEvent(
                    new TestEvent<>("你若为我繁华，你好呀：" + (i + 1))
            );
        }
    }

    @Test
    void contextLoads1() {
        for (int i = 0; i < 5; i++) {
            applicationEventPublisher.publishEvent(
                    new TestEvent<>("你若为我繁华，你好呀：" + (i + 1))
            );
        }
    }

}
