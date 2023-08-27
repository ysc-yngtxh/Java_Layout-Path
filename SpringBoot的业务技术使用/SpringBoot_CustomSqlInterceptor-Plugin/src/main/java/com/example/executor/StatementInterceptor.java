package com.example.executor;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.config.LockTables;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * Mybatis 拦截器
 *
 * @Intercepts：标识该类是一个拦截器,需要一个Signature(拦截点)参数数组。 通过Signature来指定拦截哪个对象里面的哪个方法，只有符合拦截点的条件才会进入到拦截器
 * @Signature：指明自定义拦截器需要拦截哪一个类型，哪一个方法；
 *       - type：定义拦截的类，拦截的类型具体有四种
 *              1)、Executor：        拦截执行器的方法。
 *              2)、ParameterHandler：拦截参数的处理器。
 *              3)、ResultHandler：   拦截结果集的处理器。
 *              4)、StatementHandler：拦截Sql语法构建的处理器。
 *           ** Executor 执行器中是包含有三种处理器 ParameterHandler、ResultHandler、StatementHandler
 *              我们自己在自定义Mybatis拦截器的时候，可以选择针对性的处理器进行拦截使用即可
 *              当上述三种处理器满足不了需求，就可以使用 Executor 直接定义处理流程。毕竟三种处理器能做的，Executor都能做
 *       - method：在定义拦截类的基础之上，再定义拦截的方法（可以在拦截器中预览需要的方法）
 *       - args：在定义拦截方法的基础之上在定义拦截的方法对应的参数，方法可能重载，故注意参数的类型和顺序（可以在拦截器中预览需要的方法参数）
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
        Object obj = boundSql.getParameterObject();
        String sql = boundSql.getSql();
        if (sql.trim().toUpperCase().startsWith("INSERT")) {
            ReflectUtil.setFieldValue(obj, "version", 1);
            ReflectUtil.setFieldValue(obj, "createDate", new Date());
            ReflectUtil.setFieldValue(obj, "updatedDate", new Date());
            ReflectUtil.setFieldValue(boundSql, "parameterObject", obj);
        } else if (SqlCommandType.UPDATE.equals(statement.getSqlCommandType())) {
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
                    Arrays.stream(methods).filter(item -> item.getName().equals("getVersion"))
                            .map(method -> {
                                try {
                                    versionValue[0] = method.invoke(obj);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                } catch (InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                }
                                return null;
                            }).toList();
                    Integer versionObj = (Integer) versionValue[0];
                    sql = sql.replaceAll(" set ", " SET ")
                            .replaceAll(" Set ", " SET ")
                            .replaceAll(" SET ", " SET version = version+1, updated_date = NOW(), ")
                            .replaceAll(" where ", " WHERE ")
                            .replaceAll(" WHERE ", " WHERE version = " + versionObj + " and ");

                    ReflectUtil.setFieldValue(boundSql, "sql", sql);
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
