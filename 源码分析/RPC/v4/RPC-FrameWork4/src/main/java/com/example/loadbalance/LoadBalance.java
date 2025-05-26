package com.example.loadbalance;

import com.example.common.RPC_URL;
import java.util.List;
import java.util.Random;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-08 上午11:00:00
 * @apiNote TODO
 */
public class LoadBalance {

	public static RPC_URL random(List<RPC_URL> RPCUrlList) {
		Random random = new Random();
		int nextInt = random.nextInt(RPCUrlList.size());
		return RPCUrlList.get(nextInt);
	}

}
