package com.example.controller;

import com.example.entity.Brand;
import com.example.service.BrandService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 品牌表(Brand)表控制层
 *
 * @author makejava
 * @since 2024-02-25 13:19:30
 */
@RestController
public class BrandController {

    @Resource
    private BrandService brandService;

    // @CrossOrigin注解将为请求处理类或请求处理方法提供跨域调用支持。
    // 如果我们将此注解标注类，那么类中的所有方法都将获得支持跨域的能力。使用此注解的好处是可以微调跨域行为。
    @CrossOrigin
    @GetMapping("/brand")
    public ResponseEntity<Brand> queryById(Long id) {
        return ResponseEntity.ok(this.brandService.queryById(id));
    }
}

