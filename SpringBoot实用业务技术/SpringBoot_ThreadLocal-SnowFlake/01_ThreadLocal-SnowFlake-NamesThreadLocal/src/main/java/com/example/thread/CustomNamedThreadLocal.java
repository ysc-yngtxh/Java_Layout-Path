package com.example.thread;

import com.example.utils.SnowFlakeUtils;
import org.springframework.core.NamedThreadLocal;

public class CustomNamedThreadLocal {

	// 定义链路Id的 NamedThreadLocal
	private static final NamedThreadLocal<String> linkIdHolder = new NamedThreadLocal<>("linkId");

	// 生成并存储链路Id（可在事务切面中调用）
	public static void startTransaction() {
		// 使用雪花算法生成分布式的链路Id
		String txId = String.valueOf(SnowFlakeUtils.nextId());
		linkIdHolder.set(txId);
	}

	// 获取当前链路Id
	public static String getTransactionId() {
		return linkIdHolder.get();
	}

	// 清理链路Id（事务提交或回滚后调用）
	public static void clear() {
		linkIdHolder.remove();
	}
}
