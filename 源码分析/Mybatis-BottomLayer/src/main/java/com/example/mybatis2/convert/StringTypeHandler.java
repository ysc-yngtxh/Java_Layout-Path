package com.example.mybatis2.convert;

import lombok.SneakyThrows;

import java.sql.PreparedStatement;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-05 22:50
 * @apiNote TODO String类型的TypeHandler
 */
public class StringTypeHandler implements TypeHandler<String> {

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter) {
        // 使用 setString()方法设置参数，参数类型为String
        ps.setString(i, parameter);
    }
}
