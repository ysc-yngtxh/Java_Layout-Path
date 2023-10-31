package com.example.controller;

import com.example.entity.Tb3_Order;
import com.example.service.Tb3_OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表(Tb3_Order)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:26
 */
@RestController
@RequestMapping("tbOrder")
@RequiredArgsConstructor
public class Tb3_OrderController {
    private final Tb3_OrderService tb3OrderService;

    @RequestMapping("/selectById")
    public Tb3_Order selectById() {
        return tb3OrderService.selectById();
    }

    @RequestMapping("/selectMaps")
    public List<Map<String, Object>> selectMaps() {
        return tb3OrderService.selectMaps();
    }

    @RequestMapping("/updateByVersion")
    public Integer update() {
        return tb3OrderService.updateByVersion();
    }
}