package com.example.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

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
    ThreadLocal<Connection> connectionThreadLocal = new NamedThreadLocal<>("Transactional ConnectionThreadLocal");

    @SneakyThrows
    public Connection getConnection() {
        Connection con = connectionThreadLocal.get();
        if (con != null) {
            return con;
        }
        con = dataSource.getConnection();
        // 为了体现事务，全部设置为手动提交事务
        con.setAutoCommit(false);
        connectionThreadLocal.set(con);
        return con;
    }

    @SneakyThrows
    public void cleanHolder() {
        Connection con = connectionThreadLocal.get();
        if (con != null) {
            con.close();
        }
        connectionThreadLocal.remove();
    }
}