package com.example.spi.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:00
 * @apiNote TODO
 */
public class Dog implements Animals {

    private final Logger log = LoggerFactory.getLogger(Dog.class);

    @Override
    public void sayHello() {
        log.debug("Hello, I am Dog.");
        System.out.println("Hello, I am Dog.");
    }
}
