package com.example.dao;

import com.example.entity.Province;
import com.example.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceDao {

    DButil util = new DButil();

    // 根据id获取一个完整的Province对象
    public Province queryProvinceById(Integer provinceId) {
        String sql = "select * from province where id=?";
        Connection conn = util.getConn();
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        Province province = null;
        try{
            ps.setInt(1, provinceId);
            rs = ps.executeQuery();
            while(rs.next()){
                province = new Province();
                province.setId(rs.getInt("id"));
                province.setName(rs.getString("name"));
                province.setJiancheng(rs.getString("jiancheng"));
                province.setShenghui(rs.getString("shenghui"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs,ps,conn);
        }
        return province;
    }
}
