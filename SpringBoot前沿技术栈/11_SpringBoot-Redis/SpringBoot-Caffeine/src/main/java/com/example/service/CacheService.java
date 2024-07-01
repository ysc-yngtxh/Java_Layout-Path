package com.example.service;

import com.example.pojo.Data;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-07-01 21:17
 * @apiNote TODO
 */
@Service
public class CacheService {

    @Cacheable(value = "data", key = "#id")
    public Data getData(Long id) {
        // 模拟从数据库或其他来源获取数据
        return new Data(id, "sample data");
    }

    private final Cache<Long, Data> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    // 当调用getData方法时，Caffeine缓存会首先检查缓存中是否存在对应的数据，若存在则直接返回，否则从数据库中加载数据，并将其添加到缓存中。
    public Data getDataCaffeine(Long id) {
        return cache.get(id, this::loadDataFromDatabase);
    }

    private Data loadDataFromDatabase(Long id) {
        // 模拟从数据库获取数据
        return new Data(id, "sample data");
    }
}