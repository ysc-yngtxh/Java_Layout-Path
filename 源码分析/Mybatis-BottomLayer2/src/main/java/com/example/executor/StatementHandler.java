package com.example.executor;

import com.example.parameter.ParameterHandler;
import com.example.session.Configuration;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装JDBC Statement，用于操作数据库
 */
public class StatementHandler {
    private ResultSetHandler resultSetHandler = new ResultSetHandler();

    public <T> T query(String statement, Object[] parameter, Class pojo) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Object result = null;

        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(statement);
            ParameterHandler parameterHandler = new ParameterHandler(preparedStatement);
            parameterHandler.setParameters(parameter);
            preparedStatement.execute();
            try {
                result = resultSetHandler.handle(preparedStatement.getResultSet(), pojo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (T) result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }
        // 只在try里面return会报错
        return null;
    }

    /**
     * 获取连接
     */
    @SneakyThrows
    private Connection getConnection() {
        String driver = Configuration.properties.get("jdbc.driver").toString();
        String url = Configuration.properties.get("jdbc.url").toString();
        String username = Configuration.properties.get("jdbc.username").toString();
        String password = Configuration.properties.get("jdbc.password").toString();

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
