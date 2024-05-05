package com.example.优化IF.策略模式;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 13:17
 * @apiNote TODO 客户端调用的接口
 */
public class Context {

    // 持有公共接口的引用，后续通过多态来获取【红包、购物券】的实现类
    Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public String ContextInterface() {
        return strategy.quest();
    }
}
