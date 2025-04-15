package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 游诗成
 * @date 2022/07/06
 * @apiNote
 */

// @SuppressWarnings作用：告诉编译器忽略指定的警告，不用在编译完成后出现警告信息。
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功;false=设置失败;
     */
    public boolean expire(final String key, final Integer timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     * @param key Redis键
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     * @return 超时时间
     */
    public boolean expire(final String key, final Integer timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获得缓存的基本对象
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     * @param key
     * @return
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     * @param collection 多个对象
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存List对象
     * @param key 缓存的键值
     * @param <T> 缓存的对象
     * @return 缓存的对象
     */
    public <T> List<T> getCacheList(final String key) {
        List<T> dataList = redisTemplate.opsForList().range(key, 0, -1);
        return dataList;
    }

    /**
     * 缓存Set数据
     * @param key
     * @param dataSet
     * @param <T>
     * @return
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            boundSetOperations.add(it.next());
        }
        return boundSetOperations;
    }

    /**
     * 获得缓存的Set
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        SetOperations setOperations = redisTemplate.opsForSet();
        return setOperations.members(key);
    }

    /**
     * 缓存Map数据
     * @param key
     * @param map
     * @param <T>
     * @return
     */
    public <T> void setCacheMap(final String key, final Map<String, T> map) {
        if (map != null) {
            redisTemplate.opsForHash().putAll(key, map);
        }
    }

    /**
     * 获得缓存Map数据
     * @param key
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * 往Hash中存入数据
     */
    public <T> void setCacheMapValue(final String key, final String hashKey, final T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获得Hash中的数据
     */
    public <T> T getCacheMapValue(final String key, final String hashKey) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, hashKey);
    }

    /**
     * 删除Hash中的数据
     */
    public  void delCacheMapValue(final String key, final String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 获取多个Hash中的数据
     */
    public <T> List<T> getMultiCacheMapValues(final String key, final Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 获得缓存的基本对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
