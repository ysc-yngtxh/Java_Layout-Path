package com.example.session;

import com.example.MybatisBottomLayer2Application;
import com.example.annotation.Select;
import com.example.proxy.MapperRegistry;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;

/**
 * 全局配置类
 */
public class Configuration {

	// 全局配置
	public static final Map<String, Object> properties = new HashMap<>();
	// 维护接口方法与SQL关系
	public static final Map<String, String> mappedStatements = new HashMap<>();
	// 维护接口与工厂类关系
	public static final MapperRegistry mapperRegistry = new MapperRegistry();

	// 获取所有Mapper接口的Class
	private List<Class<?>> mapperList = new ArrayList<>();
	// 获取所有Mapper接口的绝对路径
	private List<String> classList = new ArrayList<>();

	/**
	 * 初始化时解析全局配置文件
	 */
	@SneakyThrows
	public Configuration(Map<String, Object> map) {
		// 1、初始化配置
		properties.putAll(map);
		// 2、解析接口上的注解
		String mapperPackagePath = properties.get("mapper.path").toString();
		// 扫描指定包下所有的mapper接口，并存入mapperList集合
		scanPackage(mapperPackagePath);
		for (Class<?> mapper : mapperList) {
			// 解析所有的接口类方法，并且方法上存在 @Select 注解的，将 Sql语句 和 全限定名称 绑定作为Map存入
			// 并且注册当前接口
			parsingClass(mapper);
		}
	}

	/**
	 * 根据全局配置文件的Mapper接口路径，扫描所有接口
	 */
	@SneakyThrows
	private void scanPackage(String mapperPath) {
		String classPath = MybatisBottomLayer2Application.class.getResource("/").toURI().getPath();
		mapperPath = mapperPath.replace(".", File.separator);
		String mainPath = classPath + mapperPath;
		doPath(new File(mainPath));
		for (String className : classList) {
			String subPath = className.substring(className.indexOf("com/example"), className.indexOf(".class"));
			String classAbsolutePath = subPath.replace("/", ".");
			Class<?> clazz = Class.forName(classAbsolutePath);
			if (clazz.isInterface()) {
				mapperList.add(clazz);
			}
		}
	}

	/**
	 * 获取文件或文件夹下所有的类.class
	 */
	private void doPath(File file) {
		// 文件夹，遍历
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f1 : files) {
				doPath(f1);
			}
		} else {
			// 文件，直接添加
			if (file.getName().endsWith(".class")) {
				classList.add(file.getPath());
			}
		}
	}

	/**
	 * 解析Mapper接口上配置的注解（SQL语句）
	 */
	private void parsingClass(Class<?> mapper) {
		// 解析方法上的注解
		Method[] methods = mapper.getMethods();
		for (Method method : methods) {
			// 解析 @Select 注解的SQL语句
			if (method.isAnnotationPresent(Select.class)) {
				for (Annotation annotation : method.getDeclaredAnnotations()) {
					if (annotation.annotationType().equals(Select.class)) {
						// 注册接口类型+方法名（全限定名称）和SQL语句的映射关系
						String statementId = method.getDeclaringClass().getName() + "." + method.getName();
						mappedStatements.put(statementId, ((Select) annotation).value());
						// 注册接口与Mapper方法返回值的映射关系
						mapperRegistry.addMapper(mapper, method.getReturnType());
					}
				}
			}
		}
	}


	/**
	 * 根据statement判断是否存在映射的SQL
	 */
	public boolean hasStatement(String statementName) {
		return mappedStatements.containsKey(statementName);
	}

	/**
	 * 根据statement ID获取SQL
	 */
	public String getMappedStatement(String id) {
		return mappedStatements.get(id);
	}

	/**
	 * 获取代理对象
	 */
	public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
		return mapperRegistry.getMapper(clazz, sqlSession);
	}

}
