package com.example.controller;

import com.example.entity.Tb_3_Order;
import com.example.service.Tb_3_OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单详情表(Tb_3_Order)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:26
 */
@RestController
@RequestMapping("tbOrder")
@RequiredArgsConstructor
public class Tb_3_OrderController {
    private final Tb_3_OrderService tb3OrderService;

    @RequestMapping("/selectById")
    public Tb_3_Order selectById() {
        return tb3OrderService.selectById();
    }
}