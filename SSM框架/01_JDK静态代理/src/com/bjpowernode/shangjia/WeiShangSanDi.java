package com.bjpowernode.shangjia;

import com.bjpowernode.factory.UsbSanFactory;
import com.bjpowernode.service.UsbSell;

public class WeiShangSanDi implements UsbSell {

    //代理的是 闪迪，定义目标厂家类
    private UsbSanFactory factory = new UsbSanFactory();
    @Override
    public float sell(int amount) {
        //调用目标方法
        float price = factory.sell(amount);
        //只增加1元
        price = price + 1;
        return price;
    }
}
