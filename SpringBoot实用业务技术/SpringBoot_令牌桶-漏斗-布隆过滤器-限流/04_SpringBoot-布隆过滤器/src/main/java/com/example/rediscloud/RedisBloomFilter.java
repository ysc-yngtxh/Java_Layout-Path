package com.example.rediscloud;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * TODO 这里在操作redis的位图bitmap，你可能只知道redis五种数据类型，string，list，hash，set，zset，
 *          没听过bitmap，但是不要紧，你可以说他是一种新的数据类型，也可以说不是，因为他的本质还是string；
 */
@Component
public class RedisBloomFilter<T> {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 删除缓存的KEY
	 *
	 * @param key KEY
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 根据给定的布隆过滤器添加值，在添加一个元素的时候使用，批量添加的性能差
	 *
	 * @param bloomFilterHelper 布隆过滤器对象
	 * @param key               KEY
	 * @param value             值
	 * @param <T>               泛型，可以传入任何类型的value
	 */
	public <T> void add(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
		int[] offset = bloomFilterHelper.murmurHashOffset(value);
		for (int i : offset) {
			redisTemplate.opsForValue().setBit(key, i, true);
		}
	}

	/**
	 * 根据给定的布隆过滤器添加值，在添加一批元素的时候使用，批量添加的性能好，使用pipeline方式(如果是集群下，请使用优化后RedisPipeline的操作)
	 *
	 * @param bloomFilterHelper 布隆过滤器对象
	 * @param key               KEY
	 * @param valueList         值，列表
	 * @param <T>               泛型，可以传入任何类型的value
	 */
	public <T> void addList(BloomFilterHelper bloomFilterHelper, String key, List<T> valueList) {
		redisTemplate.executePipelined(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.openPipeline();
				for (T value : valueList) {
					int[] offset = bloomFilterHelper.murmurHashOffset(value);
					for (int i : offset) {
						connection.setBit(key.getBytes(), i, true);
					}
				}
				return null;
			}
		});
	}

	/**
	 * 根据给定的布隆过滤器判断值是否存在
	 *
	 * @param bloomFilterHelper 布隆过滤器对象
	 * @param key               KEY
	 * @param value             值
	 * @param <T>               泛型，可以传入任何类型的value
	 * @return 是否存在
	 */
	public <T> boolean contains(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
		int[] offset = bloomFilterHelper.murmurHashOffset(value);
		for (int i : offset) {
			if (Boolean.FALSE.equals(redisTemplate.opsForValue().getBit(key, i))) {
				return false;
			}
		}
		return true;
	}
}
