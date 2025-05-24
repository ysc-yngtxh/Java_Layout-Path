package com.example.mapper;

import com.example.annotation.Select;
import com.example.domain.User;

public interface UserMapper {

	// 这里省略了解析 #{} 的逻辑，直接使用 ? 作为占位符
	@Select("select * from user where id = ?")
	public User selectOne(Object id);

}
