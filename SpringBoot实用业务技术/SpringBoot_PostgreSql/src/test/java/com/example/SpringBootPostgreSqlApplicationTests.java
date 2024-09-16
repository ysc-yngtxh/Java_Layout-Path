package com.example;

import com.example.mapper.EceUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootPostgreSqlApplicationTests {

    @Autowired
    private EceUserMapper eceUserMapper;

    @Test
    void contextLoads() {
        System.out.println(eceUserMapper.queryById(1));
    }

}
