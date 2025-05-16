package com.example.fieldMapper;

import com.example.dto.User2;
import lombok.NonNull;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-02 21:50
 * @apiNote TODO 自定义封装
 */
// @SuppressWarnings("NullableProblems")  用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class UserFieldMapper implements FieldSetMapper<User2> {

	@Override
	public @NonNull User2 mapFieldSet(@NonNull FieldSet fieldSet) throws BindException {
		// 自定义映射逻辑
		User2 user2 = new User2();
		user2.setId(fieldSet.readLong("Id"));
		user2.setName(fieldSet.readString("name"));
		user2.setAge(fieldSet.readInt("age"));

		String addr = fieldSet.readString("province")
				+ " " + fieldSet.readString("city")
				+ " " + fieldSet.readString("area");

		user2.setAddress(addr);
		return user2;
	}
}
