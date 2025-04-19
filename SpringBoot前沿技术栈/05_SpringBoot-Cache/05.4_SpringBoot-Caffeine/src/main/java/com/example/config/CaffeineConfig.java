package com.example.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author 游家纨绔
 * @dateTime 2024-07-01 22:06
 * @apiNote TODO
 */
public class CaffeineConfig {

    /**
     * 创建基于Caffeine的Cache Manager
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = new ArrayList<>();
        Map<String, Object> map = getCacheType();
        for (Map.Entry<String, Object> node : map.entrySet()) {
            caches.add(new CaffeineCache(node.getKey(), (Cache<Object, Object>) node.getValue()));
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    /**
     * 初始化自定义缓存策略
     *    initialCapacity(100): 设置缓存的初始容量为 100 个条目。
     *    maximumSize(100): 设置缓存的最大容量为 100 个条目。
     *    expireAfterWrite(10, TimeUnit.SECONDS): 设置缓存项在写入后 10 秒过期。
     *    removalListener(): 设置缓存失效监听器，当缓存项被移除时，可以做一些额外操作。
     *    recordStats(): 启用缓存统计功能，可以记录缓存的命中率、加载时间等统计信息。
     */
    private static Map<String, Object> getCacheType() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("UnitCache", Caffeine.newBuilder()
                                     .expireAfterWrite(10, TimeUnit.SECONDS)
                                     .maximumSize(100)
                                     .recordStats()
                                     .build()
               );
        map.put("UnitCache2", Caffeine.newBuilder()
                                      // 初始容量
                                      .initialCapacity(100)
                                      // 最大缓存数量
                                      .maximumSize(500)
                                      // 缓存过期时间：写入缓存后，经过某个时间缓存失效
                                      .expireAfterWrite(60, TimeUnit.SECONDS)
                                      // 缓存失效监听器
                                      .removalListener((key, value, cause) -> System.out.println("key:" + key + " value:" + value + " cause:" + cause))
                                      // 开启统计功能
                                      .recordStats()
                                      .build()
               );
        return map;
    }
}
