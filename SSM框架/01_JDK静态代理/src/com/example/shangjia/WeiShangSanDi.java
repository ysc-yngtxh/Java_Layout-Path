package com.example.shangjia;

import com.example.factory.UsbSanFactory;
import com.example.service.UsbSell;

public class WeiShangSanDi implements UsbSell {

    // 代理的是 闪迪，定义目标厂家类
    private UsbSanFactory factory = new UsbSanFactory();
    @Override
    public float sell(int amount) {
        // 调用目标方法
        float price = factory.sell(amount);
        // 只增加1元
        price = price + 1;
        return price;
    }
}
