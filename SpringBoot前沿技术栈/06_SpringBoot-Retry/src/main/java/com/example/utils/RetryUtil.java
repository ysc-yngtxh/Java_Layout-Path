package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

@Slf4j
public class RetryUtil {

	/**
	 * 重试方法
	 */
	public static boolean retryTask() {
		int i = RandomUtils.nextInt(0, 11);
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
}
