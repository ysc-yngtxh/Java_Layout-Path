package com.example.controller;

import com.example.entity.TbOrder;
import com.example.service.TbOrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单详情表(TbOrder)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:26
 */
@RestController
@RequestMapping("tbOrder")
public class TbOrderController {

    @Resource
    private TbOrderService tbOrderService;

    @RequestMapping("/selectById")
    public TbOrder selectById() {
        return tbOrderService.selectById();
    }
}