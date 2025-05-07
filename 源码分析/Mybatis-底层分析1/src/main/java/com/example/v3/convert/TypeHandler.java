package com.example.v3.convert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-05 22:46
 * @apiNote TODO 类型转换
 */
public interface TypeHandler<T> {

    void setParameter(PreparedStatement ps, int i, T parameter);

    T getResult(ResultSet rs, String column);
}
