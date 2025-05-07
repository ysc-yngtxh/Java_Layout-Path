package com.example.spi.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-12 20:00
 * @apiNote TODO
 */
public class White implements Color {

    @Override
    public void takeColor() {
        System.out.println("Hello, I am White.");
    }
}
