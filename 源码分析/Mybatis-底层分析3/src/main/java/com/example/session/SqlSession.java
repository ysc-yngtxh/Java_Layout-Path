package com.example.session;

import com.example.executor.Executor;
import lombok.Getter;

/**
 * MeBatis的API，提供给应用层使用
 */
public class SqlSession {

	@Getter
	private Configuration configuration;

	private Executor executor;

	public SqlSession(Configuration configuration) {
		this.configuration = configuration;
		// 根据全局配置决定是否使用缓存装饰
		this.executor = configuration.newExecutor();
	}

	public <T> T getMapper(Class<T> clazz) {
		return configuration.getMapper(clazz, this);
	}

	/**
	 * @param statement Sql的全限定名称
	 * @param parameter Sql的参数
	 * @param pojo      Sql的返回类型
	 */
	public <T> T selectOne(String statement, Object[] parameter, Class<T> pojo) {
		String sql = getConfiguration().getMappedStatement(statement);
		// 打印代理对象时会自动调用toString()方法，触发invoke()
		return executor.query(sql, parameter, pojo);
	}

}
