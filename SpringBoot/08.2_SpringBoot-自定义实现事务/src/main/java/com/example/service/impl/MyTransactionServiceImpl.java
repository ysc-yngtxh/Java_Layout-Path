package com.example.service.impl;

import com.example.annotation.CustomTransaction;
import com.example.config.DataSourceConnectHolder;
import com.example.service.MyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    @CustomTransaction(rollbackFor = NullPointerException.class)
    @Override
    public void saveTest(int id) {
        saveWithParameters(id, "luozhou@gmail.com");
        saveWithParameters(id + 10, "luozhou@gmail.com");
        int str = id / 0;
    }

    // 执行sql
    private void saveWithParameters(int id, String email) {
        String sql = "insert into tb_test values(?,?)";
        Connection connection = holder.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}