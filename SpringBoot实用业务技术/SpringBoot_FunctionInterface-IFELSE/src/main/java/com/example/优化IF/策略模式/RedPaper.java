package com.example.优化IF.策略模式;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 12:57
 * @apiNote TODO 红包策略类
 */
public class RedPaper extends Strategy {

    @Override
    protected String quest() {
        return "每周末9点发放";
    }
}
