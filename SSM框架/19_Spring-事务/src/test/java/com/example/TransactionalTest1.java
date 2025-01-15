package com.example;

import com.example.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionalTest1 {

    // 执行 无 事务方法。抛出异常：商品不存在异常。结果：sale 表正常插入一条数据，goods 表插入失败
    @Test
    public void text(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService service = ac.getBean("buyService", BuyGoodsService.class);
        service.callBuy(1005, 10);
    }

    /** 按照顺序先后执行 text01 - text02 - text03 方法
     *     1、执行结果：sale 表和 goods 表分别都成功插入了一条数据，说明其中有两次操作(sale 和 goods插入)同时失败，
     *                表明其中有事务回滚机制在发挥作用(要么同时成功，要么同时失败)
     *     2、如果我们没有加上事务机制的话，sale表中的数据虽不会缺失，但是goods表中的数据会因为执行异常无法插入。
     *        从而造成数据不完整，不一致的情况。
     */

    // 执行默认事务方法。抛出异常：商品不存在异常。结果：sale 表插入失败，goods 表插入失败
    @Test
    public void text01(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService service = ac.getBean("buyService", BuyGoodsService.class);
        service.buy(1005, 10);
    }

    // 执行默认事务方法。抛出异常：商品库存不足异常。结果：sale 表插入失败，goods 表插入失败
    @Test
    public void text02(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService service = ac.getBean("buyService", BuyGoodsService.class);
        service.buy(1, 2);
    }

    // 执行默认事务方法。正常运行。结果：sale 表插入成功，goods 表插入成功
    @Test
    public void text03(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService service = ac.getBean("buyService", BuyGoodsService.class);
        service.buy(3, 3);
    }
}
