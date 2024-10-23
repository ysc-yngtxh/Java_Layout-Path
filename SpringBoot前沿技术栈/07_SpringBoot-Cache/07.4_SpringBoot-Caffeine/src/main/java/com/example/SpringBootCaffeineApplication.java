package com.example;

import com.example.service.CacheService;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootCaffeineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCaffeineApplication.class, args);
    }

    @Autowired
    private CacheService cacheService;

    @Override
    public void run(String... args) throws Exception {
        // 业务场景：当从本地缓存中通过 key 取数据时，我希望当这个 key 存在的时候，取出value值；当 key 不存在的时候，返回null。
        // 很明显，只通过注解@Cacheable、@CachePut、@CacheEvict之间的组合是很难实现这个需求，因此需要特别的使用属性 'unless'
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                switch (scanner.nextInt()) {
                    case 0:
                        System.out.println(cacheService.cacheable2("C", "123"));
                        break;
                    case 1:
                        System.out.println("获取有返回值的@Cacheable：" + cacheService.cacheable("C", "123"));
                        System.out.println("获取返回值为null的@Cacheable：" + cacheService.cacheable2("C", "123"));
                        break;
                    case 2:
                        cacheService.cachePut("C", "123");
                        System.out.println("获取有返回值的@Cacheable：" + cacheService.cacheable("C", "123"));
                        System.out.println("获取返回值为null的@Cacheable：" + cacheService.cacheable2("C", "123"));
                        break;
                    case 3:
                        cacheService.cacheEvict("C", "123");
                        System.out.println("获取返回值为null的@Cacheable：" + cacheService.cacheable2("C", "123"));
                        break;
                }
            }
        }
    }
}
