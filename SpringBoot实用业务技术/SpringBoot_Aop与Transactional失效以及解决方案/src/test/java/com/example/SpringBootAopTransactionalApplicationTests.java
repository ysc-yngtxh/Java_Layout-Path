package com.example;

import com.example.proxy.ProxyService;
import com.example.transactional.TransactionalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAopTransactionalApplicationTests {

    @Autowired
    private ProxyService proxyService;
    @Autowired
    private TransactionalServiceImpl transactionalService;

    @Test
    void contextLoads() {
        // Aop切面逻辑没有执行
        proxyService.useSigInterface();
    }

    @Test
    void contextLoads2() {
        // Aop切面逻辑生效，执行成功
        proxyService.useAllInterface();
    }

    @Test
    void contextLoads3() {
        try {
            transactionalService.saveSigUser();
        } catch (Exception e) {
            System.out.println("出错了...");
        }
        System.out.println(transactionalService.YSC);
    }
}
