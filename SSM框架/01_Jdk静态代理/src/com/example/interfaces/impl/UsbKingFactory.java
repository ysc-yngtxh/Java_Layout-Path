package com.example.interfaces.impl;

import com.example.interfaces.UsbSell;

/**
 * @author 游家纨绔
 */
// 目标类：金士顿 -- 厂家，不接受用户的单独购买
public class UsbKingFactory implements UsbSell {

	@Override
	public float sell(int amount) {
		System.out.println("目标类中的方法调用，UsbKingFactory中的sell");
		// 一个128G的U盘是 85元。
		// 后期根据amount，可以实现不同的价格，例如10000个，单击是80，50000个75
		return 85.0f;
	}

}
