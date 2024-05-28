package com.example;

import com.example.service.MyTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private MyTransactionService myTransactionService;

    @Test
    void contextLoads() throws SQLException {
        myTransactionService.saveTest("ysc");
    }

}
