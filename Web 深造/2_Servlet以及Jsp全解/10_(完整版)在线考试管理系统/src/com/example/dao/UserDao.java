package com.example.dao;

import com.example.entity.Users;
import com.example.util.DBUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class UserDao {

	private DBUtil util = new DBUtil();

	// 用户注册
	// jdbc规范中，Connection创建与销毁最浪费时间，设计tcp协议的三次握手，四次挥手
	public int add(Users user, HttpServletRequest request) {
		String sql = "insert into users(userName,password,sex,email) values(?, ?, ?, ?)";
		PreparedStatement ps = util.createStatement(sql, request);
		int result = 0;
		try {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSex());
			ps.setString(4, user.getEmail());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(request);
		}
		return result;
	}

	// 查询所有用户新消息
	public List findAll() {
		List userList = new ArrayList();
		String sql = "select * from users";
		PreparedStatement ps = util.createStatement(sql);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer userId = rs.getInt("userId");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				String email = rs.getString("email");
				Users users = new Users(userId, userName, password, sex, email);
				userList.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close();
		}
		return userList;
	}

	// 根据用户编号删除用户信息
	public int delete(String userId) {
		String sql = "delete from users where userId = ?";
		PreparedStatement ps = util.createStatement(sql);
		int result = 0;
		try {
			ps.setInt(1, Integer.valueOf(userId));
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close();
		}
		return result;
	}

	// 登陆验证
	public int login(String userName, String password) {
		String sql = "select count(*) from users where userName = ? and password = ?";
		PreparedStatement ps = util.createStatement(sql);
		ResultSet rs = null;
		int result = 0;
		try {
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close();
		}
		return result;
	}

}
