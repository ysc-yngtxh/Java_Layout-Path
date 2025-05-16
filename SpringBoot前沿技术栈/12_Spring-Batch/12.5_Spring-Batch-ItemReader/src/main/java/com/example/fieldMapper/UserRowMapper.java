package com.example.fieldMapper;

import com.example.dto.User1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-12 08:40
 * @apiNote TODO 自定义映射
 */
public class UserRowMapper implements RowMapper<User1> {

	@Override
	public User1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		User1 user1 = new User1();
		user1.setId(rs.getLong("id"));
		user1.setName(rs.getString("name"));
		user1.setAge(rs.getInt("age"));
		return user1;
	}
}
