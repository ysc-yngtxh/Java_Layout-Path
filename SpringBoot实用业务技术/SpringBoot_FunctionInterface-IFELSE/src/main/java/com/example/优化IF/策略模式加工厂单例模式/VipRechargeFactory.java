package com.example.优化IF.策略模式加工厂单例模式;

import java.util.Map;

public class VipRechargeFactory {

    static Map<String, VipRechargeStrategy> map;

    static {
        map = Map.of("充值一个月会员", new OneMonthVipStrategy(),
                     "充值三个月会员", new ThreeMonthVipStrategy(),
                     "充值六个月会员", new SixMonthVipStrategy()
                    );
    }

    public static class SingletonHolder {
        public static VipRechargeFactory vipRechargeFactory = new VipRechargeFactory();
    }

    public static VipRechargeFactory getInstance() {
        return SingletonHolder.vipRechargeFactory;
    }

    public VipRechargeStrategy getConcurrentStrategy(String priceCode) {
        return map.get(priceCode);
    }
}
