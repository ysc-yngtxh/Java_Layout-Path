package com.example;

import com.example.service.MyTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomTransactionApplicationTests {

    @Autowired
    private MyTransactionService myTransactionService;

    @Test
    void contextLoads() {
        myTransactionService.saveTest("ysc");
    }
}
