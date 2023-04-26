package com.example.springbootshirojwt.service;

import com.example.springbootshirojwt.pojo.Spu;
import com.example.springbootshirojwt.vo.PageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsService {

    PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);
}
