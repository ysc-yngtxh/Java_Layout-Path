package com.example.loadbalance;

import com.example.common.URL;
import java.util.List;
import java.util.Random;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-08 上午11:00:00
 * @apiNote TODO
 */
public class LoadBalance {

	public static URL random(List<URL> urlList) {
		Random random = new Random();
		int nextInt = random.nextInt(urlList.size());
		return urlList.get(nextInt);
	}

}
