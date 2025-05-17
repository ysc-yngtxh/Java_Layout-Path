package com.example.mapper;

import com.example.annotation.Select;
import com.example.domain.User;

public interface UserMapper {

	@Select("select * from user where id = ?")
	public User selectOne(Object id);
}
