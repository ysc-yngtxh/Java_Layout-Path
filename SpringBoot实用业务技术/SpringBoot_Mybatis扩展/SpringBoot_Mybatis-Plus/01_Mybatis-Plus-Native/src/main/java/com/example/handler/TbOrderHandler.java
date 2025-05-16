package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.Order;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-31 23:20:00
 * @apiNote TODO
 */
@Component
public class TbOrderHandler implements TypeHandler<Order> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Order parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, JSON.toJSONString(parameter));
	}

	@Override
	public Order getResult(ResultSet rs, String columnName) throws SQLException {
		Order parseOrder = JSONObject.parseObject(rs.getString(columnName), Order.class);
		return parseOrder;
	}

	@Override
	public Order getResult(ResultSet rs, int columnIndex) throws SQLException {
		Order parseOrder = JSONObject.parseObject(rs.getString(columnIndex), Order.class);
		return parseOrder;
	}

	@Override
	public Order getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Order parseOrder = JSONObject.parseObject(cs.getString(columnIndex), Order.class);
		return parseOrder;
	}
}
