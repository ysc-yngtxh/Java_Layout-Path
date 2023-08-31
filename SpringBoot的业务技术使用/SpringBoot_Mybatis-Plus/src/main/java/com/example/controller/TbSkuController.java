package com.example.controller;

import com.example.service.TbSkuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (TbSku)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:33
 */
@RestController
@RequestMapping("tbSku")
public class TbSkuController {
    /**
     * 服务对象
     */
    @Resource
    private TbSkuService tbSkuService;
}