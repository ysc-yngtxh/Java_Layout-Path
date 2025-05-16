package com.example.优化IF.策略模式加工厂单例模式;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 16:00:00
 * @apiNote TODO
 */
public class OneMonthVipStrategy implements VipRechargeStrategy {

	@Override
	public void recharge(String priceCode) {
		System.out.println("充值一个月WWE体育会员到账");
	}
}
