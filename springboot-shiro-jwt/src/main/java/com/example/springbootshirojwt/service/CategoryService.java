package com.example.springbootshirojwt.service;

import com.example.springbootshirojwt.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CategoryService {

    List<Category> queryCategoryListByPid(Long pid);

    List<Category> queryByIds(List<Long> ids);
}
