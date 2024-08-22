package com.example.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

/**
 * JDBC工具类，简化JDBC编程
 */
public class DBUtil {

    /*
      工具类中的的构造方法都是私有的
      因为工具类当中的方法都是静态的，不需要new对象，直接采用类名调用。
    */
    public DBUtil() {}
    final String url="jdbc:mysql://localhost:3306/example?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    final String user="root";
    final String password="131474";
    Connection conn = null;
    PreparedStatement ps = null;


    //-----------通过全局作用域对象得到Connection--------------start
    public Connection getCon(HttpServletRequest request){
        // 1、通过请求对象，得到全局作用域对象
        ServletContext application = request.getServletContext();
        // 2、从全局作用域对象得到map
        Map map = (Map)application.getAttribute("key1");
        // 3、从map得到一个处于空闲状态Connection（拖鞋）
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            conn = (Connection)it.next();
            boolean flag = (boolean)map.get(conn);
            if(flag){
                map.put(conn,false);
                break;
            }
        }
        return conn;
    }

    public PreparedStatement createStatement(String sql,HttpServletRequest request){
        try{
            ps = getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void close(HttpServletRequest request) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        map.put(conn,true);
    }

    //-----------通过全局作用域对象得到Connection--------------start



    /*
      将jar包中driver实现类加载到Jvm中
      静态代码块在类加载时执行，并且只执行一次
    */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 封装连接通道到创建细节
    public Connection getCon(){
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 封装交通工具创建细节
    public PreparedStatement createStatement(String sql){
        try{
            ps = getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    /*
      关闭资源
      @param conn 连接对象
      @param ps 数据库操作对象
      @param rs 结果集
    */
    public void close() {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
