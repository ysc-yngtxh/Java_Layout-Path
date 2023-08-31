package com.example.controller;

import com.example.entity.Tb_2_Sku;
import com.example.service.Tb_2_SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Tb_2_Sku selectById() {
        return tb2SkuService.selectById();
    }

    @RequestMapping("/deleteLogic")
    public Tb_2_Sku deleteLogic() {
        tb2SkuService.deleteLogic();
        return tb2SkuService.selectById();
    }
}