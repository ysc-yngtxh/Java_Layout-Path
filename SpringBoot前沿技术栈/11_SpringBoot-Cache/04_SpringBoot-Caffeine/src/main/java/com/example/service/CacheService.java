package com.example.service;

import com.example.pojo.Data;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-07-01 21:17
 * @apiNote TODO
 */
@Service
public class CacheService {

    // @Cacheable：当方法被调用时，会先检查缓存中是否存在对应的key，如果存在则直接返回缓存中的value值；
    //             如果不存在，则执行方法并将其结果存储到缓存中。
    // 当调用被注解的方法时，如果对应的键已经存在缓存，则不再执行方法体，而从缓存中直接返回。
    //             当方法返回 null 时，将不进行缓存操作。
    // @Cacheable 常用的注解属性如下：
    //      value/cacheNames：缓存组件的名字，即cacheManager 中缓存的名称。
    //      key：         缓存数据时使用的 key。默认使用方法参数值，也可以使用 SpEL 表达式进行编写。
    //      keyGenerator：和 key 二选一使用。
    //      cacheManager：指定使用的缓存管理器。
    //      condition：   在方法执行开始前检查，在符合 condition 的情况下，进行缓存。
    //      unless：      在方法执行完成后检查，在符合 unless 的情况下，不进行缓存。
    //      sync：        是否使用同步模式。若使用同步模式，在多个线程同时对一个 key 进行 load 时，其它线程将被阻塞。
    @Cacheable(value = "UnitCache",
               key = "#unitType + '-' + #unitId",
               condition = "#unitType != 'A'",
               unless = "#unitType == 'B'"
    )
    public Data cacheable(String unitType, String unitId) {
        return new Data(1L, "abc");
    }

    @Cacheable(value = "UnitCache", key = "#unitType+'-'+#unitId", unless = "#result == null")
    public Data cacheable2(String unitType, String unitId) {
        return null;
    }


    // @CachePut：表示执行该方法后，其值将作为最新结果更新到缓存中。每次都执行该方法。
    @CachePut(value = "UnitCache",
              key = "#unitType + '-' + #unitId"
    )
    public Data cachePut(String unitType, String unitId) {
        return new Data(2L, "def");
    }


    // @CacheEvict：表示执行该方法后，将触发缓存清除操作
    @CacheEvict(value = "UnitCache",
                key = "#unitType + '-' + #unitId"
                // allEntries：是否清空所有缓存，默认为 false，即清空指定 key 的缓存。
                // beforeInvocation：是否在方法执行前就清空缓存，默认为 false，即在方法执行后再清空缓存。
    )
    public void cacheEvict(String unitType, String unitId) {
        System.out.println("delete cache");
    }


    // @Caching：用于组合前三个注解，例如：
    @Caching(cacheable = @Cacheable("UnitCache"),
             evict = {@CacheEvict("UnitCache"), @CacheEvict(value = "UnitCache2", allEntries = true)})
    public Data caching(Long id) {
        return new Data(id, "sample data");
    }
}