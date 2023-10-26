package com.example.shangjia;

import com.example.factory.UsbKingFactorys;
import com.example.service.UsbSells;

public class WeiShang implements UsbSells {

    // 代理的是，金士顿，定义目标厂家类
    private UsbKingFactorys factor = new UsbKingFactorys();

    @Override
    public float sell(int amount) {
        // 调用目标方法
        float price = factor.sell(amount);
        // 只增加一元
        price= price+ 1;
        return price;
    }
}
