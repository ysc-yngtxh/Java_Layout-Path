package com.example.service.impl;

import com.example.annotation.CustomTransaction;
import com.example.config.DataSourceConnectHolder;
import com.example.service.MyTransactionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author: 游家纨绔
 * @create: 2024-05-27 22:56
 * @description:
 **/
@Service
public class MyTransactionServiceImpl implements MyTransactionService {

    @Autowired
    DataSourceConnectHolder holder;

    // 一个事务中执行两个sql插入
    @Override
    @CustomTransaction(rollbackFor = NullPointerException.class)
    public void saveTest(String name) {
        saveWithParameters(name, "luozhou@gmail.com");
        saveWithParameters(name+"-cym", "luozhou@gmail.com");
        int str = 10 / 0;
    }

    // 执行sql
    @SneakyThrows
    private void saveWithParameters(String name, String email) {
        String sql = "insert into student(name,email) values(?, ?)";
        Connection connection = holder.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.executeUpdate();
    }

    /**
     * 疑问：为什么事务回滚后，实际数据没有插入成功，但在数据库中 自增ID 却增加了？
     * 解答：数据库的自增ID是在插入操作实际执行时立即生成并分配的，而不是在事务提交时。
     *      这意味着，一旦SQL插入语句被执行，无论事务最终是否成功提交，自增ID都会增加。
     *      这是因为自增ID的生成和分配是数据库的一个独立行为，与应用程序的事务管理机制是分开的。
     */

}