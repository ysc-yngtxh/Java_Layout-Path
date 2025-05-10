package com.example;

import com.example.custom.CustomBloomFilter;
import com.example.rediscloud.BloomFilterHelper;
import com.example.rediscloud.RedisBloomFilter;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Slf4j
@SpringBootTest
class SpringBloomFilterApplicationTests {

    // TODO 布隆过滤器的巨大用处就是，能够迅速判断一个元素是否在一个集合中。
    //  使用场景:
    //      缓存穿透：将所有可能存在的数据缓存放到布隆过滤器中，当黑客访问不存在的缓存时迅速返回避免缓存及DB挂掉。
    //      解决原理：布隆过滤器 这种技术是在缓存之前再加一层屏障，里面存储数据库中存在的所有key，
    //              当业务系统进行查询请求的时候，首先去 布隆过滤器 中查询该key是否存在。
    //              若不存在，则说明数据库中也不存在该数据，因此缓存都不要查了，直接返回null。
    //              若存在，则继续执行后续的流程，先前往缓存中查询，缓存中没有的话再前往数据库中的查询。
    //     分布式系统：在分布式系统中，可以使用布隆过滤器来判断一个元素是否存在于分布式缓存中，避免在所有节点上进行查询，减少网络负载


    // TODO 1、测试自定义布隆过滤器
    @Test
    void test1() {
        CustomBloomFilter filter = new CustomBloomFilter(1000000, 5);
        // 添加一些随机字符串到布隆过滤器
        for (int i = 0; i < 10000; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                builder.append( (char) ('a' + new Random().nextInt(26)) ); // 97 + 随机数 = ASCⅡ码
            }
            filter.addElement( builder.toString() );
        }
        // 测试生成随机字符串是否在布隆过滤器中
        for (int m = 0; m < 10000; m++) {
            StringBuilder builder = new StringBuilder();
            for (int n = 0; n < 10; n++) {
                builder.append( (char) ('a' + new Random().nextInt(26)) ); // 97 + 随机数 = ASCⅡ码
            }
            if (filter.checkElement(builder.toString())) {
                // 可以搜索打印日志，存在一些随机字符串
                System.out.println(builder + " -- may be in the filter");
            } else {
                System.out.println(builder + " -- is not in the filter");
            }
        }
    }



    // TODO 2、Google的 Guava依赖 实现布隆过滤器（本地内存）
    @Test
    void test2() {

        // 1、布隆过滤器默认的误差率为 0.03。10000 → 312，误判率：3.12%
        BloomFilter<CharSequence> bloomFilter1 =
                BloomFilter.create( Funnels.stringFunnel(StandardCharsets.UTF_8), 10000);
        for (int m = 0; m < 10000; m++){
            bloomFilter1.put("" + m);
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int n = 20000; n < 30000; n++){
            if(bloomFilter1.mightContain("" + n)){
                list.add(n);
            }
        }
        System.out.println("布隆过滤器使用默认误差率时的误判数量：：：" + list.size());

        // 2、布隆过滤器设置误差率为 0.0001
        BloomFilter<CharSequence> bloomFilter2 =
                BloomFilter.create( Funnels.stringFunnel(Charsets.UTF_8), 10000, 0.0001);
        for (int i = 0; i < 10000; i++) {
            bloomFilter2.put("" + i);
        }
        List<Integer> list1 = new ArrayList<Integer>();
        for (int j = 10000; j < 20000; j++) {
            if (bloomFilter2.mightContain("" + j)) {
                list1.add(j);
            }
        }
        System.err.println("布隆过滤器使用自定义误差率时的误判数量：：：" + list1.size());


        // 3、检验误差值
        BloomFilter<String> bf =
                BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 1000000, 0.02);
        // 用于存放所有实际存在的key的 Set 对象，用于是否存在。设置初始容量为100W
        Set<String> sets = new HashSet<>(1000000);
        // 用于存放所有实际存在的key的 List 对象，用于取出。设置初始容量为100W
        List<String> lists = new ArrayList<>(1000000);
        // 插入随机字符串
        for (int i = 0; i < 1000000; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }
        // 真实存在的元素
        int rightNum = 0;
        // 误以为存在的元素
        int wrongNum = 0;
        for (int i = 0; i < 10000; i++) {
            // 0-10000之间，可以被100整除的数有100个（100的倍数）
            String data = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();
            // 这里用了might，看上去不是很自信，所以如果布隆过滤器判断存在了，我们还要去sets中实锤
            if (bf.mightContain(data)) {
                if (sets.contains(data)) {
                    rightNum++;
                    continue;
                }
                wrongNum++;
            }
        }
        BigDecimal percent = new BigDecimal(wrongNum).divide(new BigDecimal(9900), 2, RoundingMode.HALF_UP);
        BigDecimal bingo = new BigDecimal(9900 - wrongNum).divide(new BigDecimal(9900), 2, RoundingMode.HALF_UP);
        System.err.println("\n在100W个元素中，判断100个实际存在的元素，布隆过滤器认为存在的：" + rightNum);
        System.err.println("\n在100W个元素中，判断9900个实际不存在的元素，误认为存在的：" + wrongNum +
                "，" + "命中率：" + bingo + "，误判率：" + percent);
    }



    // TODO 3、Redis实现分布式布隆过滤器
    @Autowired
    private RedisBloomFilter<String> redisBloomFilter;
    @Test
    void test3() {
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
