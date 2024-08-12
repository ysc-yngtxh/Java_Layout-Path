package com.example.spi.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-12 20:44
 * @apiNote TODO
 */
public class Blue implements Color{

    private final Logger log = LoggerFactory.getLogger(Blue.class);

    @Override
    public void takeColor() {
        log.debug("Hello, I am Blue.");
        System.out.println("Hello, I am Blue.");
    }
}
