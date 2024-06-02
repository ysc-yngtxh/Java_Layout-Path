package com.example;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.session.DefaultSqlSession;
import com.example.session.SqlSessionFactory;

public class MybatisBottomLayer2Application {

	public static void main(String[] args) {
		SqlSessionFactory factory = new SqlSessionFactory();
		DefaultSqlSession sqlSession = factory.build().openSqlSession();
		// 获取包含了MapperProxy代理
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.selectOne(1);

		System.out.println("第一次查询: " + user);
		System.out.println();
		user = mapper.selectOne(1);
		System.out.println("第二次查询: " + user);
	}

}
