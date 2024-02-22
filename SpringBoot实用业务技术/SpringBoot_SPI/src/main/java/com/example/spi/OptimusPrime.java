package com.example.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:42
 * @apiNote TODO
 */
public class OptimusPrime implements Robot {
    private final Logger log = LoggerFactory.getLogger(OptimusPrime.class);
    @Override
    public void sayHello() {
        log.debug("Hello, I am Optimus Prime.");
        System.out.println("Hello, I am Optimus Prime.");
    }
}
