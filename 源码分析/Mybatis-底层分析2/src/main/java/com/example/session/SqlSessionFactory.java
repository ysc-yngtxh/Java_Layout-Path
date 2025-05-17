package com.example.session;

/**
 * 会话工厂类，用于解析配置文件，产生SqlSession
 */
public class SqlSessionFactory {

	private Configuration configuration;

	public SqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 获取SqlSession
	 */
	public SqlSession openSession() {
		return new SqlSession(configuration);
	}

}
