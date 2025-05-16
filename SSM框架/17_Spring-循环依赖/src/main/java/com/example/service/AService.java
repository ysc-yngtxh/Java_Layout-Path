package com.example.service;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 22:10:00
 * @apiNote TODO
 */
public class AService {

	private BService bService;

	public AService() {
	}

	public AService(BService bService) {
		this.bService = bService;
	}

	public void setBService(BService bService) {
		this.bService = bService;
	}

	public void testProxy() {
		System.err.println("进入 AService 类的 testProxy() 方法，且 BService 对象为：" + bService.getClass());
	}
}
