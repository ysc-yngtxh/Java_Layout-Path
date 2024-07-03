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
     */
    private static Map<String, Object> getCacheType() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("UnitCache", Caffeine.newBuilder().recordStats()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(100)
                .build());
        map.put("UnitCache2", Caffeine.newBuilder().recordStats()
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .maximumSize(600)
                .build());
        return map;
    }
}
