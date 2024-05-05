package com.example.service;

public interface BuyGoodsService {

    // 购买商品的方法，goodsId:购买商品的编号。nums:购买的数量
    void buy(Integer goodsId, Integer nums);
}
