package com.example;

import com.example.proxy.ProxyService;
import com.example.transactional.service.StudentService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAopTransactionalApplicationTests {

    @Autowired
    private ProxyService proxyService;
    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads1() {
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
        // 使用直接对象，事务不生效
        studentService.saveSigUser();
    }

    @Test
    void contextLoads4() {
        // 使用代理对象，事务生效
        studentService.saveAllUser();
    }
}
