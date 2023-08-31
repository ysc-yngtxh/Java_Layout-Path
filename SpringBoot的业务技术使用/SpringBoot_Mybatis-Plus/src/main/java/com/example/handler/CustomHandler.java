package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-31 00:30
 * @apiNote TODO
 */
@Component
public class CustomHandler implements TypeHandler<List<String>> {
    @Override
    public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
        List<String> lists = JSONObject.parseArray(rs.getString(columnName), String.class);
        return lists;
    }

    @Override
    public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        List<String> lists = JSONObject.parseArray(rs.getString(columnIndex), String.class);
        return lists;
    }

    @Override
    public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        List<String> lists = JSONObject.parseArray(cs.getString(columnIndex), String.class);
        return lists;
    }
}
