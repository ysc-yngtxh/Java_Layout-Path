package com.example;

import com.example.shangjia.TaoBao;
import com.example.shangjia.WeiShang;

public class ShopMain {
    public static void main(String[] args) {
        // 创建代理的商家taobao对象
        TaoBao taoBao = new TaoBao();
        float price = taoBao.sell(1);
        System.out.println("通过淘宝的的商家，购买U盘单价：" + price);

        WeiShang weishang = new WeiShang();
        // 通过代理类，实现购买U盘，增加了优惠券，红包等等
        float prices = weishang.sell(1);
        System.out.println("通过微商购买的价格" + prices);
    }
}