package com.example.controller;

import com.example.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2025-01-24 16:00
 * @apiNote TODO
 */
@Controller
public class CacheController {

	@Autowired
	private CacheService cacheService;

	@RequestMapping("/cache/{num}")
	public String cache(@PathVariable Integer num) {
		// 业务场景：当从本地缓存中通过 key 取数据时，我希望当这个 key 存在的时候，取出value值；当 key 不存在的时候，返回null。
		// 很明显，只通过注解@Cacheable、@CachePut、@CacheEvict之间的组合是很难实现这个需求，因此需要特别的使用属性 'unless'
		switch (num) {
			case 0:
				System.out.println(cacheService.cacheable2("C", "123"));
				break;
			case 1:
				System.out.println("获取有返回值的@Cacheable：" + cacheService.cacheable("C", "123"));
				System.out.println("获取返回值为null的@Cacheable：" + cacheService.cacheable2("C", "123"));
				break;
			case 2:
				cacheService.cachePut("C", "123");
				System.out.println("获取有返回值的@Cacheable：" + cacheService.cacheable("C", "123"));
				System.out.println("获取返回值为null的@Cacheable：" + cacheService.cacheable2("C", "123"));
				break;
			case 3:
				cacheService.cacheEvict("C", "123");
				System.out.println("获取返回值为null的@Cacheable：" + cacheService.cacheable2("C", "123"));
				break;
		}
		return "success";
	}
}
