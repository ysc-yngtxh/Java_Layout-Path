package com.cn.leyou.controller;

import com.cn.leyou.service.CategoryService;
import com.cn.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id查询商品分类
     * @param pid
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam("pid") Long pid){
        //return ResponseEntity.status(HttpStatus.OK).body(null);简写以下形式
        return ResponseEntity.ok(categoryService.queryCategoryListByPid(pid));

    }

    /**
     * 根据id查询商品分类
     * @param ids
     * @return
     */
    @GetMapping("/list/ids")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(categoryService.queryByIds(ids));
    }
}
