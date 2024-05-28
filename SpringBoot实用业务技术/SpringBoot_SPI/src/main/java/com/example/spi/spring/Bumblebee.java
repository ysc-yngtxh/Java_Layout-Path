package com.example.spi.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:43
 * @apiNote TODO
 */
public class Bumblebee implements Robot {

    private final Logger log = LoggerFactory.getLogger(Bumblebee.class);

    @Override
    public void sayHello() {
        log.debug("Hello, I am Bumblebee.");
        System.out.println("Hello, I am Bumblebee.");
    }
}
