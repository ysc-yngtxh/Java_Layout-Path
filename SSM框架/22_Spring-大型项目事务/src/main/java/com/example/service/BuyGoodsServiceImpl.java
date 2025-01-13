package com.example.service;

import com.example.mapper.GoodsDao;
import com.example.mapper.SaleDao;
import com.example.pojo.Goods;
import com.example.pojo.Sale;
import com.example.excep.NotEnoughException;

public class BuyGoodsServiceImpl implements BuyGoodsService {

    private SaleDao saleDao;
    private GoodsDao goodsDao;

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public void buy(Integer goodsId, Integer nums) {
        // 记录销售信息，向sale表添加记录
        Sale sale = new Sale();
        sale.setGid(goodsId);
        sale.setNums(nums);
        saleDao.insertSale(sale);

        // 更新库存
        Goods goods = goodsDao.selectGoods(goodsId);
        if(goods == null){
            // 商品不存在
            throw new NullPointerException("编号是：" + goodsId + ",商品不存在");
        } else if(goods.getAmount() < nums){
            // 商品库存不足
            throw new NotEnoughException("编号是：" + goodsId + ",商品库存不足");
        }
        // 修改库存了
        Goods buyGoods = new Goods();
        buyGoods.setId(goodsId);
        buyGoods.setAmount(nums);
        goodsDao.updateGoods(buyGoods);
    }
}
