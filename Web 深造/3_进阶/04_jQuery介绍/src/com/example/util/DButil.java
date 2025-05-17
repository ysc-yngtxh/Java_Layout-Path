package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DButil {

    public DButil() {}
    final String url = "jdbc:mysql://localhost:3306/springdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    final String user = "root";
    final String password = "131474";
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

	public Connection getConn() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public PreparedStatement createStatement(String sql) {
		try {
			ps = getConn().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public void close(ResultSet rs, PreparedStatement Ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
