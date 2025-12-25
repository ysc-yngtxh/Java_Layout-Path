package com.example.context;

import java.util.concurrent.ConcurrentHashMap;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-01 22:10
 * @apiNote TODO 上下文透传
 */
@Data
public class LiteFlowContext {

	private ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();

	public void add2Put(String key, Object value) {
		hashMap.put(key, value);
	}
}
