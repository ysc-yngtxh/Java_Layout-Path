package com.example.service;

import com.example.dao.GoodsDao;
import com.example.dao.SaleDao;
import com.example.domain.Goods;
import com.example.domain.Sale;
import com.example.excep.NotEnoughException;

/**
 * @author 游家纨绔
 */
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
    public void buy(Integer gid, Integer nums) {
        // 记录销售信息，向sale表添加记录
        Sale sale = new Sale();
        sale.setGid(gid);
        sale.setNums(nums);
        saleDao.insertSale(sale);

        // 更新库存
        Goods goods = goodsDao.selectGoods(gid);
        if(goods == null){
            // 商品不存在
            throw new NullPointerException("编号是：" + gid + ",商品不存在");
        } else if(goods.getAmount() < nums) {
            // 商品库存不足
            throw new NotEnoughException("编号是：" + gid + ",商品库存不足");
        }
        // 修改库存了
        Goods buyGoods = new Goods();
        buyGoods.setId(gid);
        buyGoods.setAmount(nums);
        goodsDao.updateGoods(buyGoods);
    }
}