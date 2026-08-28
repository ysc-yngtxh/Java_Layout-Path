package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

@Slf4j
public class ResilientUtil {

	/**
	 * 用于演示 Spring Resilient 的声明式重试机制。
	 */
	public static boolean ResilientTask() {
        int i = RandomGenerator.getDefault().nextInt(0, 11);
		log.info("随机生成的参数: {}", i);

		if (i == 0) {
			log.info("随机值为0，抛出参数异常");
			throw new IllegalArgumentException("参数异常");
		} else if (i == 1) {
			log.info("随机值为1，返回true");
			return true;
		} else if (i == 2) {
			log.info("随机值为2，返回false");
			return false;
		} else {
			// 随机值为其他
			log.info("大于2，抛出自定义异常");
			throw new NumberFormatException("随机值大于2，抛出远程访问异常");
		}
	}


    /**
     * 模拟不稳定的外部服务调用，用于演示 Spring Resilient 的编程式重试机制。
     */
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static String fetchData(String param) {
        int currentAttempt = counter.incrementAndGet();
        System.out.println("实际执行第 " + currentAttempt + " 次，参数: " + param);

        // 模拟前 3 次都失败，第 4 次成功 (对应 maxAttempts=4)
        if (currentAttempt < 4) {
            throw new RuntimeException("模拟网络超时，当前次数: " + currentAttempt);
        }

        // 重置计数器，方便下次测试（视业务场景而定）
        // counter.set(0);
        return "成功返回数据: " + param + ", 最终尝试次数: " + currentAttempt;
    }

}
