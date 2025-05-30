package com.example.cache;

import com.example.executor.Executor;

import java.util.HashMap;
import java.util.Map;

/**
 * 带缓存的执行器，用于装饰基本执行器
 */
public class CachingExecutor implements Executor {

	private static final Map<Integer, Object> cache = new HashMap<>();
	private Executor delegate;

	public CachingExecutor(Executor delegate) {
		this.delegate = delegate;
	}

	@Override
	public <T> T query(String statement, Object[] parameter, Class<T> pojo) {
		// 计算CacheKey
		CacheKey cacheKey = new CacheKey();
		cacheKey.update(statement);  // 更新CacheKey的SQL语句
		cacheKey.update(joinStr(parameter));  // 更新CacheKey的参数
		// 是否拿到缓存
		if (cache.containsKey(cacheKey.getCode())) {
			// 命中缓存
			System.out.println("【命中缓存】");
			return pojo.cast(cache.get(cacheKey.getCode()));
		} else {
			// 没有的话调用被装饰的SimpleExecutor从数据库查询
			Object obj = delegate.query(statement, parameter, pojo);
			cache.put(cacheKey.getCode(), obj);
			return pojo.cast(obj);
		}
	}

	// 为了命中缓存，把Object[]转换成逗号拼接的字符串，因为对象的HashCode都不一样
	public String joinStr(Object[] objs) {
		StringBuffer sb = new StringBuffer();
		if (objs != null) {
			for (Object objStr : objs) {
				sb.append(objStr.toString()).append(",");
			}
		}
		int len = sb.length();
		if (len > 0) {
			sb.deleteCharAt(len - 1);
		}
		return sb.toString();
	}

}
