package com.example.rediscloud;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;

/**
 * TODO 布隆过滤器核心类
 */
public class BloomFilterHelper<T> {

	private int numHashFunctions;
	private int bitSize;
	private Funnel<T> funnel;

	public BloomFilterHelper(int expectedInsertions) {
		this.funnel = (Funnel<T>) Funnels.stringFunnel(Charset.defaultCharset());
		bitSize = optimalNumOfBits(expectedInsertions, 0.03);
		numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
	}

	public BloomFilterHelper(Funnel<T> funnel, int expectedInsertions, double fpp) {
		this.funnel = funnel;
		bitSize = optimalNumOfBits(expectedInsertions, fpp);
		numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
	}

	public int[] murmurHashOffset(T value) {
		int[] offset = new int[numHashFunctions];

		long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
		int hash1 = (int) hash64;
		int hash2 = (int) (hash64 >>> 32);
		for (int i = 1; i <= numHashFunctions; i++) {
			int nextHash = hash1 + i * hash2;
			if (nextHash < 0) {
				nextHash = ~nextHash;
			}
			offset[i - 1] = nextHash % bitSize;
		}

		return offset;
	}

	/**
	 * 计算bit数组长度
	 */
	private int optimalNumOfBits(long n, double p) {
		if (p == 0) {
			p = Double.MIN_VALUE;
		}
		return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
	}

	/**
	 * 计算hash方法执行次数
	 */
	private int optimalNumOfHashFunctions(long n, long m) {
		return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
	}
}
