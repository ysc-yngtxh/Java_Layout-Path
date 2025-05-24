package com.example.session;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-02 16:50
 * @apiNote TODO 会话工厂建造者。用于解析 Mybatis 相关配置文件
 */
public class SqlSessionFactoryBuilder {

	private static Map<String, Object> getResult() {
		Map<String, Object> map = new HashMap<>();
		map.put("jdbc.driver", "com.mysql.cj.jdbc.Driver");
		map.put("jdbc.url", "jdbc:mysql://localhost:3306/bottom_layer?characterEncoding=utf-8&serverTimezone=UTC&rewriteBatchedStatements=true");
		map.put("jdbc.username", "root");
		map.put("jdbc.password", "131474");
		map.put("mapper.path", "com.example.mapper");
		map.put("plugin.path", "com.example.interceptor.MyPlugin");
		map.put("cache.enabled", true);
		return map;
	}

	/**
	 * build方法用于初始化Configuration，解析配置文件的工作在Configuration的构造函数中
	 */
	public SqlSessionFactory build(InputStream inputStream) {
		// 执行该方法首先肯定是解析文件流(配置文件)，然后才初始化 Configuration 类的。
		// 这里简约化，不解析文件流，直接使用固定值进行初始化。
		Configuration configuration = new Configuration(getResult());
		return new SqlSessionFactory(configuration);
	}

}
