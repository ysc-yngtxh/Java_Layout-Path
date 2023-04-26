package com.bjpowernode.shangjia;

import com.bjpowernode.factory.UsbKingFactory;
import com.bjpowernode.factory.UsbSanFactory;
import com.bjpowernode.service.UsbSell;

public class WeiShang implements UsbSell {

    //代理的是，金士顿，定义目标厂家类
    private UsbKingFactory factor = new UsbKingFactory();
    //代理的是，闪迪，定义目标厂家类
    private UsbSanFactory factors = new UsbSanFactory();

    @Override
    public float sell(int amount) {
        //调用目标方法
        float price = factor.sell(amount);
        //只增加一元
        price= price+ 1;
        return price;
    }
}
