package com.example.controller;

import com.example.entity.Tb_2_Sku;
import com.example.service.Tb_2_SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (Tb_2_Sku)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:33
 */
@RestController
@RequestMapping("tbSku")
@RequiredArgsConstructor
public class Tb_2_SkuController {
    private final Tb_2_SkuService tb2SkuService;

    @RequestMapping("/selectById")
    public Tb_2_Sku selectById(@RequestParam(required = false) Integer Id) {
        return tb2SkuService.selectById(Id);
    }

    @RequestMapping("/selectMaps")
    public List<Map<String, Object>> selectMaps() {
        return tb2SkuService.selectMaps();
    }

    @RequestMapping("/deleteLogic")
    public Tb_2_Sku deleteLogic(@RequestParam(required = false) Integer Id) {
        tb2SkuService.deleteLogic();
        return tb2SkuService.selectById(Id);
    }
}