package com.example.controller;

import com.example.entity.Brand;
import com.example.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系(Brand)表控制层
 * @author 游家纨绔
 * @since 2023-09-02 14:48:25
 */
@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @RequestMapping("/selectAll")
    public List<Brand> selectAll() {
        return brandService.selectAll();
    }

    @RequestMapping("/selectByName")
    public Brand selectByName() {
        return brandService.selectByName();
    }

    @RequestMapping("/insertNameAndLetter")
    public Brand insertNameAndLetter() {
        return brandService.insertNameAndLetter();
    }
}
