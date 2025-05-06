package com.example.interceptor;

import com.example.annotation.IgnoreTenantId;
import com.example.tenant.TenantContextHolder;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Objects;
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
@Intercepts({
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class ExecutorTenantInterceptor implements Interceptor {
	private static final Logger log = LoggerFactory.getLogger(ExecutorTenantInterceptor.class);

	// 每一次 CRUD 操作都会经过mybatis拦截器，拦截器对象有以上四个，并且依次顺序执行
	// 每经过一个拦截器对象就会调用插件的plugin方法，也就是说该方法会调用4次。根据 @Intercepts 注解来决定是否进行拦截处理
	@Override
	public Object plugin(Object target) {
		log.info("ExecutorTenantInterceptor Plugin >>>>>>> {}", target);
		// 判断一下目标类型，是本插件要拦截的对象时才执行 Plugin.wrap 方法，否则的话，直接返回目标本身
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
		log.info("触发自定义的Mybatis拦截器 Executor");

		// TODO 第一部分：解析我们的目标代理对象（好吧，我承认这里大部分代码并不需要，但留着便于理解）
		// 获取目标对象，就是 @Signature 注解中的 type 值
		Executor executor = (Executor) invocation.getTarget();
		// 获取目标对象参数，就是 @Signature 注解中的 args 值
		Object[] args = invocation.getArgs();
		// 获取的 MappedStatement对象 对应Mapper.xml配置文件中的一个select/update/insert/delete节点
		MappedStatement statements = (MappedStatement) args[0];
		// 获取实体类操作对象 (类型先不转为具体的User)
		Object parameter = args[1];
		// RowBounds类：是用来将所有符合条件的数据全都查询到内存中，然后在内存中对数据进行分页，也就是逻辑分页
		// 但是这里我们并没有进行内存配置，仅仅只是获取一个对象而已
		RowBounds rowBounds = (RowBounds) args[2];
		// 获取 结果集拦截器
		ResultHandler resultHandler = (ResultHandler) args[3];
		// 这里是 缓存Key值 的引用。
		// Mybatis框架有一级、二级缓存，缓存作为键值对存在的key部分是由sql语句和sql参数信息组成的一个对象，value则是结果集
		CacheKey cacheKey;
		// 获取在该拦截器下绑定的 Sql 引用
		BoundSql boundSql;
		// 当拦截器是 4 个参数时
		if (args.length == 4) {
			// 通过我们的 MappedStatement对象 获取绑定的 Sql对象
			boundSql = statements.getBoundSql(parameter);
			// 在我们的拦截器中创建 缓存Key对象，里面信息有：数据库操作对象statement、sql参数、内存分页设置、sql语句
			cacheKey = executor.createCacheKey(statements, parameter, rowBounds, boundSql);
		} else {
			// 当拦截器是6个参数时，从参数中获取已有的 Sql对象 和 缓存key值对象
			cacheKey = (CacheKey) args[4];
			boundSql = (BoundSql) args[5];
		}

		// TODO 第二部分：所有的查询 sql 都会默认在 where 语句中拼接条件 Tenant_id = ?
		//  如果在 Mapper 层的查询方法上加有 @IgnoreTenantId 的注解，则不进行条件 Tenant_id = ? 拼接
		// 原本查询的 sql语句
		String oldSql = boundSql.getSql();
		// if ( !(oldSql.contains("where") || oldSql.contains("WHERE")) ) {
		//     throw new SqlException(SqlEnum.SQL_QUERY_WHERE_NULL);
		// }
		// 获取节点的id属性加命名空间,其实就是全限定名称加唯一标识Id的路径（com.example.mapper.UserMapper.queryById）
		String id = statements.getId();
		// 获取 Class 路径
		String clazzName = id.substring(0, id.lastIndexOf('.'));
		// 获取 Method 名称
		String mapperMethod = id.substring(id.lastIndexOf('.') + 1);
		Class<?> clazz;
		try {
			clazz = Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			return null;
		}
		// 通过反射获取 该全限定名称路径 下的所有方法
		Method[] methods = clazz.getMethods();
		IgnoreTenantId ignoreTenantId = null;
		for (Method method : methods) {
			if (method.getName().equals(mapperMethod)) {
				ignoreTenantId = method.getAnnotation(IgnoreTenantId.class);
				break;
			}
		}
		if (Objects.isNull(ignoreTenantId)) {
			// 获取 tenant_id 租户值
			Integer tenant = TenantContextHolder.getUser().getTenantId();
			// 这里用JSqlParser工具来修改 where条件，组装新的 sql语句
			Select select = (Select) CCJSqlParserUtil.parse(oldSql);
			PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
			Expression where = plainSelect.getWhere();
			if (Objects.isNull(where)) {
				EqualsTo equalsTo = new EqualsTo();
				equalsTo.withLeftExpression(new Column("tenant_id"));
				equalsTo.withRightExpression(new LongValue(tenant));
				plainSelect.setWhere(equalsTo);
			} else {
				plainSelect.setWhere(new AndExpression(where
						, CCJSqlParserUtil.parseCondExpression("tenant_id = " + tenant)));
			}
			// 重新new一个查询Sql语句对象
			BoundSql newBoundSql = new BoundSql(statements.getConfiguration(), select.toString(),
			                                    boundSql.getParameterMappings(), boundSql.getParameterObject());
			// 从旧的Sql对象中获取属性映射字段，放入到新的Sql对象中
			for (ParameterMapping mapping : boundSql.getParameterMappings()) {
				String prop = mapping.getProperty();
				if (boundSql.hasAdditionalParameter(prop)) {
					newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
				}
			}
			// 把新的Sql查询对象放到statement里
			MappedStatement newStatement = newMappedStatement(statements, new BoundSqlSource(newBoundSql));
			args[0] = newStatement;
			if (args.length == 6) {
				args[5] = newStatement.getBoundSql(parameter);
			}
		}

		// TODO 第三部分：使目标代理对象程序继续往后执行
		log.info("Mapper方法是：{}", statements.getId());
		return invocation.proceed();
	}

	private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new
				MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
			builder.keyProperty(ms.getKeyProperties()[0]);
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	/**
	 * 定义一个内部辅助类，作用是包装 SQL
	 */
	class BoundSqlSource implements SqlSource {
		private BoundSql boundSql;

		public BoundSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub 不怎么使用这个方法
		log.error("Mybatis Intercept Properties  >>>>>>> {}", "prop1");
	}
}
