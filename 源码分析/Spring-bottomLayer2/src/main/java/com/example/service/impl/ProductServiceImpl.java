package com.example.service.impl;

import com.example.service.ItemService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-20 23:49
 * @apiNote TODO 商品各类
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ItemService itemService;

    @Override
    public void test(){
        double price = itemService.price();
        System.out.println("商品价格：" + price);
    }
}
