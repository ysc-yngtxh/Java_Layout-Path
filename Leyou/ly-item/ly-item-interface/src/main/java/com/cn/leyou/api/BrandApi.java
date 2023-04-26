package com.cn.leyou.api;

import com.cn.leyou.pojo.Brand;
import com.cn.leyou.vo.PageResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/brand")
public interface BrandApi {
    @GetMapping("/page")
    PageResult<Brand> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    );

    @GetMapping("/{id}")
    Brand queryBrandById(@PathVariable("id") Long id);
}

