package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.TbOrder;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-31 23:23
 * @apiNote TODO
 */
public class TbOrderHandler implements TypeHandler<TbOrder> {
    @Override
    public void setParameter(PreparedStatement ps, int i, TbOrder parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public TbOrder getResult(ResultSet rs, String columnName) throws SQLException {
        TbOrder parseTbOrder = JSONObject.parseObject(rs.getString(columnName), TbOrder.class);
        return parseTbOrder;
    }

    @Override
    public TbOrder getResult(ResultSet rs, int columnIndex) throws SQLException {
        TbOrder parseTbOrder = JSONObject.parseObject(rs.getString(columnIndex), TbOrder.class);
        return parseTbOrder;
    }

    @Override
    public TbOrder getResult(CallableStatement cs, int columnIndex) throws SQLException {
        TbOrder parseTbOrder = JSONObject.parseObject(cs.getString(columnIndex), TbOrder.class);
        return parseTbOrder;
    }
}
