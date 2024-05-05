package com.example.proxy.taobao;

import com.example.interfaces.UsbSell;
import com.example.interfaces.impl.UsbSanDiFactory;

public class TaoBaoSanDi implements UsbSell {

    private UsbSanDiFactory factorys = new UsbSanDiFactory();

    // 实现销售U盘功能
    @Override
    public float sell(int amount) {
        // 向厂家发送订单，告诉厂家，我买了U盘，厂家发货
        float price = factorys.sell(amount); //厂家的价格
        // 商家 需要加价，也就是代理要增加价格
        price = price+25; // 增强功能，代理类在完成目标类方法调用后，增强了功能。
        // 在目标类的方法调用后，你做的其他功能，都是增强的意思
        System.out.println("淘宝商家，给你一个优惠券，或者红包");

        // 增加的价格
        return price;
    }
}
