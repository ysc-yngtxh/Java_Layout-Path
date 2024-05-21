package com.example.service.impl;

import com.example.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-21 00:33
 * @apiNote TODO
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public double price() {
        return 1.22;
    }
}
