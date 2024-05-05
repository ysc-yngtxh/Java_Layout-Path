package com.example;

import com.example.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {

    // 抛出商品不存在异常
    @Test
    public void text01(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService service = (BuyGoodsService) ac.getBean("buyService");
        service.buy(1005, 10);
    }

    // 抛出商品库存不足异常
    @Test
    public void text02(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService service = (BuyGoodsService) ac.getBean("buyService");
        service.buy(1001, 500);
    }

    // 正常运行
    @Test
    public void text03(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService service = (BuyGoodsService) ac.getBean("buyService");
        service.buy(1001, 50);
    }
    /**
     * 1、在先后执行text01--text03后，我们可以发现在我们的sale表中缺少了两条数据(id值跳了两个数)，表明事务回滚执行(删除异常数据)
     * 2、而如果我们没有加上事务机制的话，我们的sale表中的数据是不会缺失的
     * 3、这么做是为了让数据保存得更完善，处理起来更方便。
     */
}
