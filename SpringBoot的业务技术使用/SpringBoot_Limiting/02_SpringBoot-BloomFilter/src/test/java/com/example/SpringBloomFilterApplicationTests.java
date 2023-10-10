package com.example;

import com.example.custom.CustomBloomFilter;
import com.example.rediscloud.BloomFilterHelper;
import com.example.rediscloud.RedisBloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
class SpringBloomFilterApplicationTests {

    // TODO 布隆过滤器的巨大用处就是，能够迅速判断一个元素是否在一个集合中。
    //  使用场景:
    //         缓存穿透：将所有可能存在的数据缓存放到布隆过滤器中，当黑客访问不存在的缓存时迅速返回避免缓存及DB挂掉。
    //         解决原理：布隆过滤器 这种技术是在缓存之前再加一层屏障，里面存储数据库中存在的所有key，
    //                 当业务系统进行查询请求的时候，首先去 布隆过滤器 中查询该key是否存在。
    //                 若不存在，则说明数据库中也不存在该数据，因此缓存都不要查了，直接返回null。
    //                 若存在，则继续执行后续的流程，先前往缓存中查询，缓存中没有的话再前往数据库中的查询。
    @Test
    void test1() {
        // TODO 测试自定义布隆过滤器
        CustomBloomFilter filter = new CustomBloomFilter(1000000, 5);
        // 添加一些随机字符串到布隆过滤器
        for (int i = 0; i < 10000; i++) {
            filter.addElement(generateRandomString(10));
        }
        // 检查一些随机字符串是否在布隆过滤器中
        for (int i = 0; i < 100; i++) {
            String randomString = generateRandomString(10);
            if (filter.checkElement(randomString)) {
                System.out.println(randomString + " may be in the filter.");
            } else {
                System.out.println(randomString + " is not in the filter.");
            }
        }
    }

    /**
     * 生成一个随机字符串
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append( (char) ('a' + random.nextInt(26)) );
        }
        return builder.toString();
    }


    @Autowired
    private RedisBloomFilter<String> redisBloomFilter;
    @Test
    void test2() {
        // TODO 测试Redis实现分布式布隆过滤器
        int expectedInsertions = 1000;
        double fpp = 0.1;
        redisBloomFilter.delete("bloom");
        BloomFilterHelper<CharSequence> bloomFilterHelper =
                new BloomFilterHelper<>(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, fpp);
        int j = 0;
        // 添加100个元素
        List<String> valueList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            valueList.add(i + "");
        }
        long beginTime = System.currentTimeMillis();
        redisBloomFilter.addList(bloomFilterHelper, "bloom", valueList);
        long costMs = System.currentTimeMillis() - beginTime;
        log.info("布隆过滤器添加{}个值，耗时：{}ms", 100, costMs);
        for (int i = 0; i < 1000; i++) {
            boolean result = redisBloomFilter.contains(bloomFilterHelper, "bloom", i + "");
            if (!result) {
                j++;
            }
        }
        log.info("漏掉了{}个,验证结果耗时：{}ms", j, System.currentTimeMillis() - beginTime);
    }
    // 注意这里用的是addList，它的底层是pipelining管道，而add方法的底层是一个个for循环的setBit，这样的速度效率是很慢的，
    // 但是他能有返回值，知道是否插入成功，而pipelining是不知道的，所以具体选择用哪一种方法看你的业务场景，以及需要插入的速度决定；
}
