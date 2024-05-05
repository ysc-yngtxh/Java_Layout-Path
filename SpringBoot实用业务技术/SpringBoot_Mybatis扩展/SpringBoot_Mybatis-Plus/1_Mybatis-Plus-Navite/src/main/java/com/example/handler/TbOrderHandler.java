package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.Tb3_Order;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-31 23:23
 * @apiNote TODO
 */
@Component
public class TbOrderHandler implements TypeHandler<Tb3_Order> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Tb3_Order parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Tb3_Order getResult(ResultSet rs, String columnName) throws SQLException {
        Tb3_Order parseTb3Order = JSONObject.parseObject(rs.getString(columnName), Tb3_Order.class);
        return parseTb3Order;
    }

    @Override
    public Tb3_Order getResult(ResultSet rs, int columnIndex) throws SQLException {
        Tb3_Order parseTb3Order = JSONObject.parseObject(rs.getString(columnIndex), Tb3_Order.class);
        return parseTb3Order;
    }

    @Override
    public Tb3_Order getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Tb3_Order parseTb3Order = JSONObject.parseObject(cs.getString(columnIndex), Tb3_Order.class);
        return parseTb3Order;
    }
}
