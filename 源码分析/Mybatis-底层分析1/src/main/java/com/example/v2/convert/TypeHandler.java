package com.example.v2.convert;

import java.sql.PreparedStatement;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-05 22:50:00
 * @apiNote TODO 类型转换
 */
public interface TypeHandler<T> {

	void setParameter(PreparedStatement ps, int i, T parameter);

}
