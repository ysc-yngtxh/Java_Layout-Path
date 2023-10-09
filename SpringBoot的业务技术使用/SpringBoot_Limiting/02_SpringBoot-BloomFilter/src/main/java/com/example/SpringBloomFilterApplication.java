package com.example;

import com.example.filter.BloomFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBloomFilterApplication {

    // TODO 布隆过滤器的巨大用处就是，能够迅速判断一个元素是否在一个集合中。
    //  使用场景:
    //         缓存穿透：将所有可能存在的数据缓存放到布隆过滤器中，当黑客访问不存在的缓存时迅速返回避免缓存及DB挂掉。
    //         解决原理：布隆过滤器 这种技术是在缓存之前再加一层屏障，里面存储数据库中存在的所有key，
    //                 当业务系统进行查询请求的时候，首先去 布隆过滤器 中查询该key是否存在。
    //                 若不存在，则说明数据库中也不存在该数据，因此缓存都不要查了，直接返回null。
    //                 若存在，则继续执行后续的流程，先前往缓存中查询，缓存中没有的话再前往数据库中的查询。
    public static void main(String[] args) {
        SpringApplication.run(SpringBloomFilterApplication.class, args);
        
        BloomFilter filter = new BloomFilter(1000000, 5);
        // 添加一些随机字符串到布隆过滤器
        for (int i = 0; i < 10000; i++) {
            filter.addElement(BloomFilter.generateRandomString(10));
        }
        // 检查一些随机字符串是否在布隆过滤器中
        for (int i = 0; i < 100; i++) {
            String randomString = BloomFilter.generateRandomString(10);
            if (filter.checkElement(randomString)) {
                System.out.println(randomString + " may be in the filter.");
            } else {
                System.out.println(randomString + " is not in the filter.");
            }
        }
    }
}
