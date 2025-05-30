package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {

	private static SqlSessionFactory factory = null;

	static {
		String config = "mybatis.xml"; // 需要和你得项目中的文件名一样
		try (InputStream in = Resources.getResourceAsStream(config)) {

			// 创建SqlSessionFactory对象，使用SqlSessionFactoryBuild
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// 获取SqlSession的方法
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		if (factory != null) {
			sqlSession = factory.openSession();  // 默认写法：非自动提交事务
			// sqlSession = factory.openSession(true); 自动提交事务
		}
		return sqlSession;
	}
}
