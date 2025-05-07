package com.example.spi.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:00
 * @apiNote TODO
 */
public class Honey implements Food {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Honey.");
    }
}
