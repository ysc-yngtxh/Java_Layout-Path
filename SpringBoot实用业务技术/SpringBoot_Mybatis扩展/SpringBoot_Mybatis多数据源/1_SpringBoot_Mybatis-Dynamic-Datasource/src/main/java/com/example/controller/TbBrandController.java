package com.example.controller;

import com.example.entity.TbBrand;
import com.example.service.TbBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系(TbBrand)表控制层
 * @author 游家纨绔
 * @since 2023-09-02 14:48:25
 */
@RestController
@RequestMapping("tbBrand")
@RequiredArgsConstructor
public class TbBrandController {

    private final TbBrandService tbBrandService;

    @RequestMapping("/selectAll")
    public List<TbBrand> selectAll(){
        return tbBrandService.selectAll();
    }

    @RequestMapping("/selectByName")
    public TbBrand selectByName(){
        return tbBrandService.selectByName();
    }

    @RequestMapping("/insertNameAndLetter")
    public TbBrand insertNameAndLetter(){
        return tbBrandService.insertNameAndLetter();
    }
}