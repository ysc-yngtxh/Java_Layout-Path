package com.example.google;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-10 00:09
 * @apiNote TODO 2、Guava实现布隆过滤器（本地内存）
 */
@Slf4j
public class UseGoogleBloomFilter {
    // 插入100W数据
    private static final int insertions = 1000000;

    // 期望的误判率
    private static double fpp = 0.02;

    public static void main(String[] args) {

        // 初始化一个存储string数据的布隆过滤器,默认误判率是0.03。Guava中官方类 BloomFilter
        BloomFilter<String> bf =
                BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions, fpp);

        // 用于存放所有实际存在的key的 Set 对象，用于是否存在。设置初始容量为100W
        Set<String> sets = new HashSet<>(insertions);

        // 用于存放所有实际存在的key的 List 对象，用于取出。设置初始容量为100W
        List<String> lists = new ArrayList<>(insertions);

        // 插入随机字符串
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int rightNum = 0;
        int wrongNum = 0;

        for (int i = 0; i < 10000; i++) {
            // 0-10000之间，可以被100整除的数有100个（100的倍数）
            String data = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();

            // 这里用了might,看上去不是很自信，所以如果布隆过滤器判断存在了,我们还要去sets中实锤
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
        log.info("在100W个元素中，判断100个实际存在的元素，布隆过滤器认为存在的：" + rightNum);
        log.info("在100W个元素中，判断9900个实际不存在的元素，误认为存在的：" + wrongNum +
                "，" + "命中率：" + bingo + "，误判率：" + percent);
    }
}
