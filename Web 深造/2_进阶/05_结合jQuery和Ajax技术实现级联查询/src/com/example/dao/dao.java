package com.example.dao;

import com.example.entity.City;
import com.example.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dao {

    // 查询所有的省份，简称，以及城市
    public List<Province> provinceList(){
        List<Province> list = new ArrayList<>();
        String sql = "select * from province order by id";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Province p = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","131474");
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                p = new Province();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setJiancheng(rs.getString("jiancheng"));
                p.setShenghui(rs.getString("shenghui"));
                list.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    // 根据请求头中的省份id查询city表的城市名
    public List<City> cityList(Integer provinceId){
        City city = null;
        List<City> list = new ArrayList<>();

        String sql = "select id,name from city where provinceid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","131474");
            ps = conn.prepareStatement(sql);
            ps.setInt(1,provinceId);
            rs = ps.executeQuery();
            while(rs.next()) {
                city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                list.add(city);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
