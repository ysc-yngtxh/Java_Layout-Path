package com.example.shangjia;

import com.example.factory.UsbKingFactory;
import com.example.service.UsbSell;

public class WeiShang implements UsbSell {

    // 代理的是，金士顿，定义目标厂家类
    private UsbKingFactory factor = new UsbKingFactory();

    @Override
    public float sell(int amount) {
        // 调用目标方法
        float price = factor.sell(amount);
        // 只增加一元
        price= price+ 1;
        return price;
    }
}
