package com.example.interceptor;

import com.example.advice.SqlEnum;
import com.example.advice.SqlException;
import com.example.config.AllowTables;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Intercepts({
		@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class ExecutorWhereInterceptor implements Interceptor {
	private static final Logger log = LoggerFactory.getLogger(ExecutorWhereInterceptor.class);

	// 获取允许全表操作的表信息
	final AllowTables allowTables;

	public ExecutorWhereInterceptor(AllowTables allowTables) {
		this.allowTables = allowTables;
	}

	// 每一次 CRUD 操作都会经过mybatis拦截器，拦截器对象有以上四个，并且依次顺序执行
	// 每经过一个拦截器对象就会调用插件的plugin方法，也就是说该方法会调用4次。根据 @Intercepts 注解来决定是否进行拦截处理
	@Override
	public Object plugin(Object target) {
		log.info("ExecutorTenantInterceptor Plugin >>>>>>> {}", target);
		// 判断一下目标类型，是本插件要拦截的对象时才执行Plugin.wrap方法，否则的话，直接返回目标本身
		// 我们要拦截的对象是定义在 @Signature 中的第一个参数 type 值。根据第二个参数 method 值了解到针对的是查询请求
		if (target instanceof Executor) {
			// 返回一个拦截器代理对象，这里的 this 就是我们的 ExecutorTenantInterceptor 类，作为代理对象会去执行我们重写的拦截 intercept() 方法
			return Plugin.wrap(target, this);
		}
		// 返回目标对象，表示执行自己原有的内部拦截逻辑，也就不会去执行我们定义的 intercept(Invocation invocation) 方法
		return target;
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		// 判断是否启用 全表更新/删除 操作的where限制
		if (!allowTables.isEnable()) {
			return invocation.proceed();
		}

		final Object[] args = invocation.getArgs();
		MappedStatement statement = (MappedStatement) args[0];
		Object parameterObject = args[1];
		BoundSql boundSql = statement.getBoundSql(parameterObject);
		// 获取 更新/删除 执行sql
		String sql = boundSql.getSql();

		// 更新、删除操作的sql语句没有 where 子句的时候抛出异常。避免全表更新/删除
		if (SqlCommandType.UPDATE.equals(statement.getSqlCommandType())) {
			// 这里用JSqlParser工具来判断是否存在where条件
			Update update = (Update) CCJSqlParserUtil.parse(sql);
			// 获取配置文件中的允许全表更新的表集合
			List<String> updateTable = allowTables.getUpdate();
			// 不在配置表集合中，且 sql没有 where子句的抛出异常
			if (!updateTable.contains(update.getTable().getName())) {
				if (Objects.isNull(update.getWhere())) {
					throw new SqlException(SqlEnum.SQL_UPDATE_WHERE_NULL);
				}
			}
		} else if (SqlCommandType.DELETE.equals(statement.getSqlCommandType())) {
			// 这里用JSqlParser工具来判断是否存在where条件
			Delete delete = (Delete) CCJSqlParserUtil.parse(sql);
			// 获取配置文件中的允许全表删除的表集合
			List<String> deleteTable = allowTables.getDelete();
			// 不在配置表集合中，且 sql没有 where子句的抛出异常
			if (!deleteTable.contains(delete.getTable().getName())) {
				if (Objects.isNull(delete.getWhere())) {
					throw new SqlException(SqlEnum.SQL_DELETE_WHERE_NULL);
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
