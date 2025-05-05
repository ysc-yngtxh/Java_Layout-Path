package com.example.优化IF.策略模式加工厂单例模式;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 15:58
 * @apiNote TODO
 */
public class ThreeMonthVipStrategy implements VipRechargeStrategy {

    @Override
    public void recharge(String priceCode) {
        System.out.println("充值三个月WWE体育会员到账");
    }
}
