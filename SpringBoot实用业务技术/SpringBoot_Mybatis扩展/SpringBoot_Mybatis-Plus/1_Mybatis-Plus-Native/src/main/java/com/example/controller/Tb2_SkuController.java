package com.example.controller;

import com.example.entity.Tb2_Sku;
import com.example.service.Tb2_SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (Tb2_Sku)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 19:56:33
 */
@RestController
@RequestMapping("tbSku")
@RequiredArgsConstructor
public class Tb2_SkuController {
    private final Tb2_SkuService tb2SkuService;

    @RequestMapping("/selectById")
    public Tb2_Sku selectById(@RequestParam(required = false) Integer Id) {
        return tb2SkuService.selectById(Id);
    }

    @RequestMapping("/selectMaps")
    public List<Map<String, Object>> selectMaps() {
        return tb2SkuService.selectMaps();
    }

    @RequestMapping("/deleteLogic")
    public Tb2_Sku deleteLogic(@RequestParam(required = false) Integer id) {
        tb2SkuService.deleteLogic();
        return tb2SkuService.selectById(id);
    }
}