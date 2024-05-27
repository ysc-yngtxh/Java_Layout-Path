package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: 游家纨绔
 * @create: 2024-05-27 22:56
 * @description:
 **/
@Component
public class DataSourceConnectHolder {

    @Autowired
    DataSource dataSource;

    // 线程绑定对象
    ThreadLocal<Connection> resources = new NamedThreadLocal<>("Transactional resources");

    public Connection getConnection() {
        Connection con = resources.get();
        if (con != null) {
            return con;
        }
        try {
            con = dataSource.getConnection();
            // 为了体现事务，全部设置为手动提交事务
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resources.set(con);
        return con;
    }

    public void cleanHolder() {
        Connection con = resources.get();
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resources.remove();
    }
}