package com.example.session;

import com.example.MybatisBottomLayer3Application;
import com.example.annotation.Select;
import com.example.proxy.MapperRegistry;
import com.example.cache.CachingExecutor;
import com.example.executor.Executor;
import com.example.executor.SimpleExecutor;
import com.example.plugin.Interceptor;
import com.example.plugin.InterceptorChain;
import lombok.SneakyThrows;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 所有Mapper接口
	private List<Class<?>> mapperList = new ArrayList<>();
	// 类所有文件
	private List<String> classList = new ArrayList<>();

	// 插件
	private InterceptorChain interceptorChain = new InterceptorChain();

	/**
	 * 初始化时解析全局配置文件
	 */
	@SneakyThrows
	public Configuration(Map<String, Object> map) {
		// 1、初始化配置
		properties.putAll(map);

		// 2、解析接口上的注解（会覆盖properties中的接口与实体类的关系）
		String mapperPackagePath = properties.get("mapper.path").toString();
		scanPackage(mapperPackagePath);
		for (Class<?> mapper : mapperList) {
			parsingClass(mapper);
		}

		// 3、解析插件，可配置多个插件
		String pluginPathValue = properties.get("plugin.path").toString();
		String[] pluginPaths = pluginPathValue.split(",");
		if (pluginPaths != null) {
			// 将插件添加到interceptorChain中
			for (String plugin : pluginPaths) {
				Interceptor interceptor = (Interceptor) Class.forName(plugin).getDeclaredConstructor().newInstance();
				interceptorChain.addInterceptor(interceptor);
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

	public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
		return mapperRegistry.getMapper(clazz, sqlSession);
	}

	/**
	 * 创建执行器，当开启缓存时使用缓存装饰。当配置插件时，使用插件代理
	 */
	public Executor newExecutor() {
		Executor executor = null;
		if (properties.get("cache.enabled").equals("true")) {
			executor = new CachingExecutor(new SimpleExecutor());
		} else {
			executor = new SimpleExecutor();
		}

		// 目前只拦截了Executor，所有的插件都对Executor进行代理，没有对拦截类和方法签名进行判断
		if (interceptorChain.hasPlugin()) {
			return (Executor) interceptorChain.pluginAll(executor);
		}
		return executor;
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
						// 注册接口类型 + 方法名和SQL语句的映射关系（全限定名称）
						String statement = method.getDeclaringClass().getName() + "." + method.getName();
						mappedStatements.put(statement, ((Select) annotation).value());
						// 注册接口与实体类的映射关系
						mapperRegistry.addMapper(mapper, method.getReturnType());
					}
				}
			}
		}
	}

	/**
	 * 根据全局配置文件的Mapper接口路径，扫描所有接口
	 */
	@SneakyThrows
	private void scanPackage(String mapperPath) {
		String classPath = MybatisBottomLayer3Application.class.getResource("/").getPath();
		mapperPath = mapperPath.replace(".", File.separator);
		String mainPath = classPath + mapperPath;
		doPath(new File(mainPath));
		for (String className : classList) {
			className = className.replace(classPath.replace("/", "\\").replaceFirst("\\\\", ""), "").replace("\\", ".").replace(".class", "");
			Class<?> clazz = Class.forName(className);
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

}
