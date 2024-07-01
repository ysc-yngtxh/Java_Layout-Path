package com.example;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootCaffeineApplicationTests {

    @Test
    @SneakyThrows
    void contextLoads1() {
        // 创建一个缓存实例
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(100)
                .build();

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

    // 权重
    // 在某些场景下，咱们可能需要根据条目的大小而不是数量来限制缓存。比如说，如果缓存的是图片或文件，它们的大小可能相差很大。这时候，就可以用Caffeine的权重功能了。
    @Test
    void contextLoads2() {
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
    void contextLoads3() {
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
    void contextLoads4() {
        Cache<String, String> cache = Caffeine.newBuilder()
            .recordStats()
            .build();

        cache.put("键1", "值1");
        System.out.println("Caffeine中是否命中 '键1'：" + cache.getIfPresent("键1")); // 命中
        System.out.println("Caffeine中是否命中 '键2'：" + cache.getIfPresent("键2")); // 未命中

        System.out.println(cache.stats()); // 打印统计信息
    }
}
