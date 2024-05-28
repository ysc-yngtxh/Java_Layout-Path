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

}