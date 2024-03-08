package com.example.spi.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:43
 * @apiNote TODO
 */
public class Bus implements Car {
    private final Logger log = LoggerFactory.getLogger(Bus.class);
    @Override
    public void sayHello() {
        log.debug("Hello, I am Bus.");
        System.out.println("Hello, I am Bus.");
    }
}
