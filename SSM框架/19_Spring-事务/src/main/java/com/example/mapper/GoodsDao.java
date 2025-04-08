package com.example.mapper;

import com.example.pojo.SSMGoods;

public interface GoodsDao {

    // 更新库存。goods表示本次用户购买的商品信息，id，购买数量
    int updateGoods(SSMGoods SSMGoods);

    // 查询商品的信息
    SSMGoods selectGoods(Integer id);
}
