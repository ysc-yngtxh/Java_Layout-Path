package com.example.v3.convert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.SneakyThrows;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-05 22:50:00
 * @apiNote TODO Integer类型的TypeHandler
 */
public class IntegerTypeHandler implements TypeHandler<Integer> {

	@SneakyThrows
	@Override
	public void setParameter(PreparedStatement ps, int i, Integer parameter) {
		// 使用 setInt()方法设置参数，参数类型为Integer
		ps.setInt(i, parameter);
	}

	@SneakyThrows
	@Override
	public Integer getResult(ResultSet rs, String column) {
		return rs.getInt(column);
	}

}
