package com.example.utils;

import java.util.HashMap;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BucketUtil {

	// 默认容量10
	static final int DEFAULT_MAX_COUNT = 10;
	// 默认增长速率为1
	static final int DEFAULT_CREATE_RATE = 1;
	// 使用HashMap存放令牌桶，这里默认为10个令牌桶
	public static HashMap<String, BucketUtil> buckets = new HashMap<>(10);

	// 自定义容量，一旦创建不可改变
	final int maxCount;
	// 自定义增长速率1s几个令牌
	int createRate;
	// 当前令牌数
	int size = 0;


	// 默认令牌桶的容量及增长速率
	public BucketUtil() {
		maxCount = DEFAULT_MAX_COUNT;
		createRate = DEFAULT_CREATE_RATE;
	}

	// 自定义令牌桶容量及增长速率
	public BucketUtil(int maxCount, int createRate) {
		this.maxCount = maxCount;
		this.createRate = createRate;
	}

	public boolean isFull() {
		return size == maxCount;
	}

	// 根据速率自增生成一个令牌
	public synchronized void incrTokens() {
		for (int i = 0; i < createRate; i++) {
			if (isFull()) {
				return;
			}
			size++;
		}
	}

	// 取一个令牌
	public synchronized boolean getToken() {
		if (size > 0) {
			size--;
		} else {
			return false;
		}
		return true;
	}
}
