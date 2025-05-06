package com.example.controller;

import com.example.entity.Order;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表(Order)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:26
 */
@RestController
@RequestMapping("tbOrder")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;

    @RequestMapping("/selectById")
    public Order selectById() {
        return orderService.selectById();
    }

    @RequestMapping("/selectMaps")
    public List<Map<String, Object>> selectMaps() {
        return orderService.selectMaps();
    }

    @RequestMapping("/updateByVersion")
    public Integer update() {
        return orderService.updateByVersion();
    }
}
