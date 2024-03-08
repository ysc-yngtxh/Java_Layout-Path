package com.example.spi.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:42
 * @apiNote TODO
 */
public class Motorcycle implements Car {
    private final Logger log = LoggerFactory.getLogger(Motorcycle.class);
    @Override
    public void sayHello() {
        log.debug("Hello, I am Motorcycle.");
        System.out.println("Hello, I am Motorcycle.");
    }
}
