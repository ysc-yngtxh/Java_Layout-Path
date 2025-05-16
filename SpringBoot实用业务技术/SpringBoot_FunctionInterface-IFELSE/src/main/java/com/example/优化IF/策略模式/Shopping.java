package com.example.优化IF.策略模式;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 13:00:00
 * @apiNote TODO 购物券策略类
 */
public class Shopping extends Strategy {

	@Override
	protected String quest() {
		return "每周三9点发放";
	}
}
