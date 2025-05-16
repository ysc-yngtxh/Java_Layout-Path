package com.example.prepared;

import com.example.dto.User;
import lombok.NonNull;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-16 17:30
 * @apiNote TODO 设置占位符参数
 */
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class UserPreStatementSetter implements ItemPreparedStatementSetter<User> {

	@Override
	public void setValues(@NonNull User item, @NonNull PreparedStatement ps) throws SQLException {
		ps.setLong(1, item.getId());
		ps.setString(2, item.getName());
		ps.setLong(3, item.getAge());
	}
}
