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
