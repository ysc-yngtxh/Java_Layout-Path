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

    // 在 Spring Boot 应用程序中，HikariCP 是默认的数据库连接池实现，这一变化始于 Spring Boot 2.0 版本。
    // HikariCP 是一个高性能的 JDBC 连接池，它被设计用于优化内存使用，提供最快的连接速度，并确保良好的稳定性。
    // 选择 HikariCP 作为默认是因为相较于其他连接池如 C3P0、DBCP 或 Tomcat JDBC 连接池，HikariCP 在性能测试中表现更优。
    @Autowired
    DataSource dataSource; // 默认的 DataSource 为 HikariDataSource。hikari连接池名称默认为 HikariPool-1

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