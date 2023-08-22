package com.example.springbootshirojwt.controller;

import com.example.springbootshirojwt.pojo.Spu;
import com.example.springbootshirojwt.service.GoodsService;
import com.example.springbootshirojwt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //分页查询Spu
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value="page",defaultValue = "1") Integer page,      // 当前页数
            @RequestParam(value="rows",defaultValue = "5") Integer rows,      // 数据显示行数
            @RequestParam(value="saleable",required = false) Boolean saleable,// 是否上架
            @RequestParam(value="key",required = false) String key){          // 搜索字段

        return ResponseEntity.ok(goodsService.querySpuByPage(page, rows, saleable, key));
    }
}
