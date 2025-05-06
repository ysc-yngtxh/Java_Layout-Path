package com.example.interceptor;

import cn.hutool.core.util.ReflectUtil;
import com.example.config.LockTables;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/* TODO Mybatis 拦截器
 *      @Intercepts：标识该类是一个拦截器,需要一个Signature(拦截点)参数数组。通过Signature来指定拦截哪个对象里面的哪个方法，只有符合拦截点的条件才会进入到拦截器
 *      @Signature：指明自定义拦截器需要拦截哪一个类型，哪一个方法；
 *          - type：定义拦截的类，拦截的类型具体有四种
 *                 (1)、Executor：        拦截执行器的方法 -- 针对Sql是 查询/更新/插入/删除 的拦截
 *                 (2)、StatementHandler：拦截Sql语法构建的处理器 -- 针对Sql的构建拦截
 *                 (3)、ParameterHandler：拦截参数的处理器 -- 针对Sql的参数拦截
 *                 (4)、ResultHandler：   拦截结果集的处理器 -- 针对Sql返回结果拦截
 *              ** Executor 执行器中是包含有三种处理器 StatementHandler、ParameterHandler、ResultHandler
 *                 我们自己在自定义Mybatis拦截器的时候，可以选择针对性的处理器进行拦截使用即可
 *                 当上述三种处理器满足不了需求，就可以使用 Executor 直接定义处理流程。毕竟三种处理器能做的，Executor都能做
 *          - method：在定义拦截类的基础之上，再定义拦截的方法（可以在拦截器中预览需要的方法）
 *          - args：在定义拦截方法的基础之上在定义拦截的方法对应的参数，方法可能重载，故注意参数的类型和顺序（可以在拦截器中预览需要的方法参数）
 */
@Intercepts(
		@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
)
public class StatementInterceptor implements Interceptor {
	private static final Logger log = LoggerFactory.getLogger(StatementInterceptor.class);

	// 获取有悲观锁的表，有就不用去判断了
	private final LockTables lockTables;

	public StatementInterceptor(LockTables lockTables) {
		this.lockTables = lockTables;
	}

	// 每一次 CRUD 操作都会经过mybatis拦截器，拦截器对象有以上四个，并且依次顺序执行
	// 每经过一个拦截器对象就会调用插件的plugin方法，也就是说该方法会调用4次。根据 @Intercepts 注解来决定是否进行拦截处理
	@Override
	public Object plugin(Object target) {
		log.info("StatementInterceptor Plugin >>>>>>> {}", target);
		// 判断一下目标类型，是本插件要拦截的对象时才执行Plugin.wrap方法，否则的话，直接返回目标本身
		// 我们要拦截的对象是定义在 @Signature 中的第一个参数 type 值
		if (target instanceof StatementHandler) {
			// 返回一个拦截器代理对象，这里的 this 就是我们的 StatementInterceptor 类，作为代理对象会去执行我们重写的拦截 intercept() 方法
			return Plugin.wrap(target, this);
		}
		// 返回目标对象，表示执行自己原有的内部拦截逻辑，也就不会去执行我们定义的 intercept(Invocation invocation) 方法
		return target;
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("触发自定义的Mybatis拦截器 StatementHandler");

		// 在 plugin方法中我们已经对拦截器对象进行过滤,所以能走到这里说明拦截器只能是 StatementHandler
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// 通过反射获取
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		MappedStatement statement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

		BoundSql boundSql = statementHandler.getBoundSql();
		// 这里获取的是DTO实体类操作对象
		Object obj = boundSql.getParameterObject();
		String sql = boundSql.getSql();
		// 这里判断是 insert语句还是update语句：insert提供字符串拼接sql的方式；update使用工具JSqlParser解析的方式组装sql
		if (sql.trim().toUpperCase().startsWith("INSERT")) {
			ReflectUtil.setFieldValue(obj, "version", 1);
			ReflectUtil.setFieldValue(obj, "createDate", new Date());
			ReflectUtil.setFieldValue(obj, "updatedDate", new Date());
			ReflectUtil.setFieldValue(boundSql, "parameterObject", obj);
		} else if (SqlCommandType.UPDATE.equals(statement.getSqlCommandType())) {
			/** 乐观锁实现思路：
			 *      1. 判断语句类型，仅支持update
			 *      2. 获取数据对象，提取出version值，设该值为oldVersion，令其+1设该值为newVersion
			 *      3. 获取SQL语句，用JSqlParser工具修改version参数值为newVersion，
			 *         添加version的查询条件：where version = oldVersion
			 *      4. 将新的SQL语句覆盖进去，然后继续执行
			 */
			// 这里用JSqlParser工具来解析 sql 语句
			Update update = (Update) CCJSqlParserUtil.parse(sql);
			// 判断当前操作表是否已经有悲观锁。如果有，那么就没必要使用乐观锁了
			if (!lockTables.getPessimistic().contains(update.getTable().getName())) {
				Class<?> clazz = obj.getClass();
				Field[] fields = clazz.getDeclaredFields();
				Method[] methods = clazz.getDeclaredMethods();
				// 判断该表是否有乐观锁字段
				if (Arrays.stream(fields).map(Field::getName).toList().contains("version")) {
					final Object[] versionValue = {null};
					// 通过反射getVersion方法获取返回值
					Arrays.stream(methods).forEach(item -> {
						if (item.getName().equals("getVersion")) {
							try {
								versionValue[0] = item.invoke(obj);
							} catch (IllegalAccessException | InvocationTargetException e) {
								throw new RuntimeException(e);
							}
						}
					});
					Integer versionObj = (Integer) versionValue[0];
					// 添加 SET ... and version = version+1, updated_date = NOW()
					update.addUpdateSet(new Column("version"), new LongValue("version+1"));
					update.addUpdateSet(new Column("updated_date"), new StringValue(LocalDateTime.now().toString()));
					// WHERE ... and version = ?
					update.setWhere(new AndExpression(update.getWhere()
							, CCJSqlParserUtil.parseCondExpression(" version = " + versionObj)));

					// TODO 这里为什么可以直接重写 sql 就可以，而 Executor 执行器却要重新构建MappedStatement？
					// MappedStatement中属性BoundSql是加有final关键字的。BoundSql修改后，通过对象引用获取sql值与反射获取到的sql值是不一样的
					// Executor 执行完后还有处理器StatementHandler，处理器从BoundSql对象中获取sql数据，是旧的数据
					// 处理器StatementHandler执行完，Statement 则是通过反射获取sql，获取的是新数据
					// 因此，Executor修改完sql后需要重新包装BoundSql对象，StatementHandler修改完sql只需要重新赋值BoundSql类中的属性sql
					ReflectUtil.setFieldValue(boundSql, "sql", update.toString());
				}
			}
		}
		return invocation.proceed();
	}

	@Override
	public void setProperties(Properties properties) {
		Interceptor.super.setProperties(properties);
	}
}
