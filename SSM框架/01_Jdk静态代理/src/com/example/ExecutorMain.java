package com.example;

import com.example.proxy.taobao.TaoBaoKing;
import com.example.proxy.weishang.WeiShangKing;

public class ExecutorMain {

	public static void main(String[] args) {
		// 创建代理的商家taobao对象
		TaoBaoKing taoBaoKing = new TaoBaoKing();
		float price = taoBaoKing.sell(1);
		System.out.println("通过淘宝的的商家，购买U盘单价：" + price);

		WeiShangKing weiShangKing = new WeiShangKing();
		// 通过代理类，实现购买U盘，增加了优惠券，红包等等
		float prices = weiShangKing.sell(1);
		System.out.println("通过微商购买的价格" + prices);
	}

}
