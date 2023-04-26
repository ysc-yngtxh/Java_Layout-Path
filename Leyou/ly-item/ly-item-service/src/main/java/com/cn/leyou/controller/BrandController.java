package com.cn.leyou.controller;

import com.cn.leyou.service.BrandService;
import com.cn.leyou.pojo.Brand;
import com.cn.leyou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value="page",defaultValue = "1") Integer page,
            @RequestParam(value="rows",defaultValue = "5") Integer rows,
            @RequestParam(value="sortBy",required = false) String sortBy,
            @RequestParam(value="desc",defaultValue = "false") Boolean desc,
            @RequestParam(value="key",required = false) String key
    ){
        return ResponseEntity.ok(brandService.queryBrandByPage(page,rows,sortBy,desc,key));
    }


    /**
     * 新增商品
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam("cids") List<Long> cids){
        //这里的新增商品不需要返回值，所以我们泛型里写上Void.
        brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        //因为我们不需要返回值，所以可以不去写body，写上build会更合适
    }

    /**
     * 根据cid查询商品分类
     * @param cid
     * @return
     */
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCId(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id") Long id){
        return ResponseEntity.ok(brandService.queryBrandById(id));
    }
}
