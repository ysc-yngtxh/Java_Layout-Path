package com.example.spi.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:00
 * @apiNote TODO
 */
public class Honey implements Food {

    private final Logger log = LoggerFactory.getLogger(Honey.class);

    @Override
    public void sayHello() {
        log.debug("Hello, I am Honey.");
        System.out.println("Hello, I am Honey.");
    }
}
