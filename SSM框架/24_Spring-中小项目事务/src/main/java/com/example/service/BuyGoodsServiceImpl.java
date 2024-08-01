package com.example.service;

import com.example.dao.GoodsDao;
import com.example.dao.SaleDao;
import com.example.domain.Goods;
import com.example.domain.Sale;
import com.example.excep.NotEnoughException;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * @Transactional (
     *       propagation = Propagation.REQUIRED,
     *       isolation = Isolation.DEFAULT,
     *       readOnly = false,
     *       rollbackFor = {
     *           NullPointerException.class, NotEnoughException.class
     *       }
     * )
     * rollbackFor:表示发生指定的异常才会回滚。
     *    处理逻辑是：
     *      1)、spring框架会首先检查方法抛出的异常是不是在rollbackFor的属性值中
     *          如果异常在rollbackFor列表中，不管是什么类型的异常，一定回滚。
     *      2)、如果你的抛出的异常不在rollbackFor列表中，spring会判断异常是不是RuntimeException,
     *          如果是一定回滚
     */
    // 使用的是事务控制的默认值，默认的传播行为是REQUIRED，默认的隔离级别DEFAULT
    // 默认抛出运行时异常，回滚事务
    @Transactional  // 使用该注解需要先到xml文件中进行事务配置
    @Override
    public void buy(Integer goodsId, Integer nums) {
        //记录销售信息，向sale表添加记录
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
