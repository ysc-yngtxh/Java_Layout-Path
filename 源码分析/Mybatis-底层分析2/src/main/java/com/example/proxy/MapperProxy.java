package com.example.proxy;

import com.example.session.SqlSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MapperProxy代理类，用于代理Mapper接口
 */
public class MapperProxy implements InvocationHandler {

	private SqlSession sqlSession;
	private Class<?> object;

	public MapperProxy(SqlSession sqlSession, Class<?> object) {
		this.sqlSession = sqlSession;
		this.object = object;
	}

	/**
	 * 所有Mapper接口的方法调用都会走到这里
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// Mapper接口类型+方法名（全限定名称），作为SQL的唯一标识
		String mapperInterface = method.getDeclaringClass().getName();
		String methodName = method.getName();
		String statementId = mapperInterface + "." + methodName;

		// 如果根据接口类型 + 方法名能找到映射的SQL，则执行SQL
		if (sqlSession.getConfiguration().hasStatement(statementId)) {
			return sqlSession.selectOne(statementId, args, object);
		}
		// 否则直接执行被代理对象的原方法
		// return method.invoke(proxy, args);
		// 该步骤还没完全处理，感谢读者指出~
		return method.invoke(object.newInstance(), args);
	}

}
