package com.example.springbootshirojwt.service;

import com.example.springbootshirojwt.pojo.Brand;
import com.example.springbootshirojwt.vo.PageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BrandService {

    PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    void saveBrand(Brand brand,List<Long> cids);

    Brand queryBrandById(Long id);

    List<Brand> queryBrandByCid(Long cid);
}

