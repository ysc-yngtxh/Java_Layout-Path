package com.example.controller;

import com.example.entity.TbSku;
import com.example.service.TbSkuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (TbSku)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:33
 */
@RestController
@RequestMapping("tbSku")
public class TbSkuController {
    @Resource
    private TbSkuService tbSkuService;

    @RequestMapping("/selectById")
    public TbSku selectById() {
        return tbSkuService.selectById();
    }
}