package com.example.dao;

import com.example.entity.Province;
import com.example.util.DButil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceDao {
    DButil util = new DButil();

    //访问数据库,根据id获取名称
    public String queryproviceNameById(Integer proviceId){
        String sql = "select name from province where id=?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        String name = "";
        try{
            ps.setInt(1,proviceId);
            rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            util.close();
        }
        return name;
    }

    // 根据id获取一个完整的Province对象
    public Province queryproviceById(Integer proviceId){
        String sql = "select * from province where id=?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        Province province = null;
        try{
            ps.setInt(1,proviceId);
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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            util.close();
        }
        return province;
    }
}
