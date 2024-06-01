package com.example;

import com.example.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyAppTests {

    @Autowired
    private TransactionService transactionService;

    // 在同一个类中，事务方法调用非事务方式的方法，没有出现预期的异常报错(事务失效)
    @Test
    public void text01(){
        transactionService.saveTransaction();
    }

    // 利用 AopContext.currentProxy()，从而解决事务失效问题
    @Test
    public void text02(){
        transactionService.saveAopContextTransaction();
    }

    // 将非事务方式的方法择出来作为单独的一个类，并注入到事务方法的类中进行调用，从而解决事务失效问题。
    @Test
    public void text03(){
        transactionService.updateNonTransactional();
    }
}
