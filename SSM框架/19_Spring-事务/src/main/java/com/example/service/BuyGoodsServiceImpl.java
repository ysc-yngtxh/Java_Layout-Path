package com.example.service;

import com.example.excep.NotEnoughException;
import com.example.mapper.GoodsDao;
import com.example.mapper.SaleDao;
import com.example.pojo.Goods;
import com.example.pojo.Sale;
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


	// 这里调用同类中默认的事务方法，会导致其事务失效。因为事务是通过代理对象实现的，而不是通过真实对象(this)的方法
	public void callBuy(Integer goodsId, Integer nums) {
		this.buy(goodsId, nums);
	}

    /**
     * @Transactional (
     *      propagation = Propagation.REQUIRED,
     *      isolation = Isolation.DEFAULT,
     *      readOnly = false,
     *      rollbackFor = {
     *          NullPointerException.class, NotEnoughException.class
     *      }
     * )
     * rollbackFor：表示发生指定的异常才会回滚。
     *    处理逻辑是：
     *      1)、Spring框架会首先检查方法抛出的异常是不是在rollbackFor的属性值中
     *          如果异常在rollbackFor列表中，不管是什么类型的异常，一定回滚。
     *      2)、如果你的抛出的异常不在rollbackFor列表中，Spring会判断异常是不是RuntimeException，如果是则回滚
     */
    // 使用的是事务控制的默认值。
    // 默认的传播行为是REQUIRED，默认的隔离级别为DEFAULT(将会使用数据库默认的事务隔离级别)。默认抛出运行时异常，回滚事务
    @Transactional  // 使用该注解需要先到xml文件中进行事务配置
    @Override
    public void buy(Integer goodsId, Integer nums) {
        // 记录销售信息，向sale表添加记录
        Sale Sale = new Sale();
        Sale.setGid(goodsId);
        Sale.setNums(nums);
        saleDao.insertSale(Sale);

		// 更新库存
		Goods Goods = goodsDao.selectGoods(goodsId);
		if (Goods == null) {
			// 商品不存在
			throw new NullPointerException("编号是：" + goodsId + ",商品不存在");
		} else if (Goods.getAmount() < nums) {
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
