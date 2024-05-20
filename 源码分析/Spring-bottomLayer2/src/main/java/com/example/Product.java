package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-20 23:49
 * @apiNote TODO 商品各类
 */
@Service
public class Product {

    @Autowired
    private Hello hello;

    public void test(){
        System.out.println("商品");
    }
}
