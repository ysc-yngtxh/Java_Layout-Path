package com.example.funnel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 游家纨绔
 * @date 2023/10/12 00:00
 * @apiNote TODO 漏斗限流算法
 */
public class FunnelRateLimiter {

	private Map<String, Funnel> funnelMap = new ConcurrentHashMap<>();

	/**
	 * 根据给定的漏斗参数检查是否允许访问
	 *
	 * @param username   用户名
	 * @param action     操作
	 * @param capacity   漏斗容量
	 * @param allowQuota 每 {perSecond} 秒内水滴的流量
	 * @param perSecond  单位时间（秒）
	 * @return 是否允许访问
	 */
	public boolean isActionAllowed(String username, String action, int capacity, int allowQuota, int perSecond) {
		String key = "funnel:" + action + ":" + username;
		if (!funnelMap.containsKey(key)) {
			funnelMap.put(key, new Funnel(capacity, allowQuota, perSecond));
		}
		Funnel funnel = funnelMap.get(key);
		// 设置漏斗中剩余水的容量至少为1，才能满足一次请求
		return funnel.watering(1);
	}
}
