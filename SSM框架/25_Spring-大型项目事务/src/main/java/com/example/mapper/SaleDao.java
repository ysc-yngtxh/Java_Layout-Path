package com.example.mapper;

import com.example.pojo.Sale;

public interface SaleDao {

    // 增加销售记录
    int insertSale(Sale sale);
}
