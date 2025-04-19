package com.example;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalListener;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootCaffeineApplicationTests {

    // Cache
    // 在获取缓存值时，如果想要在缓存值不存在时，原子的将值写入缓存，可以调用get(key, k->value)方法，该方法避免写入竞争
    // 调用 invalidate()方法，将手动移除缓存。
    // 多线程情况下，当使用 get(key, k->value)时，如果有另一个线程同时调用本方法进行竞争，则后一线程会被阻塞，直到前一线程更新缓存完成；
    // 而若另一线程调用 getIfPresent()方法，则会立即返回 null，不会被阻塞。
    @Test
    @SneakyThrows
    void test1() {
        System.out.println("Step 0: Cache created");
        // 创建一个缓存实例
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(100)
                .build();
        System.out.println("Step 1: Cache created");

        // 往缓存里放一些数据
        cache.put("关键词1", "值1");
        cache.put("关键词2", "值2");

        // 从缓存中取数据
        String value1 = cache.getIfPresent("关键词1");
        System.out.println("获取到的值：" + value1);

        // 缓存中的条目数量
        System.out.println("缓存中的条目数量：" + cache.estimatedSize()); // 输出：4

        // 模拟一下数据过期的情况
        TimeUnit.SECONDS.sleep(2);

        String valueExpired = cache.getIfPresent("关键词1");
        System.out.println("过期后的值：" + valueExpired); // 这里应该是null，因为已经过期了
    }

    // LoadingCache 是一种自动加载的缓存。
    // 其和普通缓存不同的地方在于，当缓存不存在时，若调用 get()方法，则会自动调用 CacheLoader.load() 方法加载最新值。
    // 调用 getAll() 方法将遍历所有的 key 调用 get()，除非实现了 CacheLoader.loadAll()方法。
    // 使用LoadingCache时，需要指定CacheLoader，并实现其中的 load()方法供缓存缺失时自动加载。
    // 多线程情况下，当两个线程同时调用 get()则后一线程将被阻塞，直至前一线程更新缓存完成。
    @Test
    public void test2() {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .build(new CacheLoader<>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "hello " + key;
                    }

                    @Override
                    public Map<? extends String, ? extends String> loadAll(Set<? extends String> keys) throws Exception {
                        return CacheLoader.super.loadAll(keys);
                    }
                });
    }

    // AsyncCache
    // AsyncCache 是 Cache 的一个变体。其响应结果均为 CompletableFuture，
    // 通过这种方式，AsyncCache 对异步编程模式进行了适配。
    // 默认情况下，缓存计算使用 ForkJoinPool.commonPool()作为线程池，如果想要指定线程池，则可以覆盖并实现 Caffeine.executor(Executor)方法。
    // 多线程情况下，当两个线程同时调用 get(key, k->value)，则会返回同一个 CompletableFuture 对象。
    // 由于返回结果本身不进行阻塞，可以根据业务设计自行选择阻塞等待或者非阻塞。
    @Test
    @SneakyThrows
    public void test3() {
        AsyncCache<String, String> asyncCache = Caffeine.newBuilder().buildAsync();
        CompletableFuture<String> completableFuture = asyncCache.get("key", key -> "abc");
        // 阻塞，直至缓存更新完成
        completableFuture.get();
    }

    // AsyncLoadingCache
    // 显然这是 Loading Cache 和 Async Cache 的功能组合。AsyncLoadingCache 支持以异步的方式，对缓存进行自动加载。
    // 类似 LoadingCache，同样需要指定 CacheLoader，并实现其中的 load()方法供缓存缺失时自动加载，
    // 该方法将自动在ForkJoinPool.commonPool()线程池中提交。
    // 如果想要指定 Executor，则可以实现 AsyncCacheLoader().asyncLoad()方法。
    @Test
    @SneakyThrows
    public void test4() {
        AsyncLoadingCache<String, Object> cache = Caffeine.newBuilder()
                .buildAsync(new AsyncCacheLoader<String, Object>() {
                    // 自定义线程池加载
                    @Override
                    public CompletableFuture<?> asyncLoad(String s, Executor executor) throws Exception {
                        return null;
                    }
                });
                /*.build(new CacheLoader<String, Object>() {
                    // 使用默认线程池加载
                    @Override
                    public String load(String key) throws Exception {
                        return "abc";
                    }
                });*/

        CompletableFuture<Object> completableFuture = cache.get("key");
        completableFuture.get();
    }


    // 权重
    // 在某些场景下，咱们可能需要根据条目的大小而不是数量来限制缓存。比如说，如果缓存的是图片或文件，它们的大小可能相差很大。这时候，就可以用Caffeine的权重功能了。
    @Test
    void contextLoads1() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumWeight(10000)
                .weigher((key, value) -> value.toString().length())
                .build();

        cache.put("长文本", "这是一段超级超级长的文本...");
        cache.put("长文本", "这是一段超级超级超级超级长的文本...");
        cache.put("长文本", "这是一段超级超级超级超级超级超级超级超级长的文本...");
        cache.put("长文本", "这是一段超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级长的文本...");
        // 这里的权重是文本长度

        System.out.println(cache.getIfPresent("长文本"));
    }

    // 监听器
    // Caffeine还支持自定义监听器，这可以用来监听缓存条目的创建、更新和删除事件。
    @Test
    void contextLoads2() {
        RemovalListener<String, String> listener = (key, value, cause) ->
            System.out.println("被移除的键：" + key + ", 原因：" + cause);

        Cache<String, String> cache = Caffeine.newBuilder()
                .removalListener(listener)
                .build();

        cache.put("键1", "值1");
        cache.invalidate("键1"); // 手动移除，触发监听器
    }

    // 统计信息
    // 了解缓存的性能和状态对于调优和故障排查是非常重要的。Caffeine提供了详尽的统计信息，包括命中率、加载时间等等。
    @Test
    void contextLoads3() {
        Cache<String, String> cache = Caffeine.newBuilder()
            .recordStats()
            .build();

        cache.put("键1", "值1");
        System.out.println("Caffeine中是否命中 '键1'：" + cache.getIfPresent("键1")); // 命中
        System.out.println("Caffeine中是否命中 '键2'：" + cache.getIfPresent("键2")); // 未命中

        System.out.println(cache.stats()); // 打印统计信息
    }
}
