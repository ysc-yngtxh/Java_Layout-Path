package com.example.interceptor;

import cn.hutool.core.util.ReflectUtil;
import com.example.holder.TenantContextHolder;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

/**
 * @Intercepts：标识该类是一个拦截器,需要一个Signature(拦截点)参数数组。
 *              通过Signature来指定拦截哪个对象里面的哪个方法，只有符合拦截点的条件才会进入到拦截器
 * @Signature：指明自定义拦截器需要拦截哪一个类型，哪一个方法；
 *       - type：定义拦截的类，拦截的类型具体有四种
 *                 1)、Executor：拦截执行器的方法。
 *                 2)、ParameterHandler：拦截参数的处理。
 *                 3)、ResultHandler：拦截结果集的处理。
 *                 4)、StatementHandler：拦截Sql语法构建的处理。
 *       - method：在定义拦截类的基础之上，在定义拦截的方法
 *       - args：在定义拦截方法的基础之上在定义拦截的方法对应的参数，JAVA里面方法可能重载，故注意参数的类型和顺序
 * */
@Intercepts({
        // @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class SqlInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(SqlInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        Object obj = boundSql.getParameterObject();
        String sql = boundSql.getSql();
        if (sql.trim().toUpperCase().startsWith("INSERT")) {
            ReflectUtil.setFieldValue(obj, "rev", 0);
            ReflectUtil.setFieldValue(obj, "createTime", new Date());
            ReflectUtil.setFieldValue(obj, "operateTime", new Date());
            ReflectUtil.setFieldValue(boundSql, "parameterObject", obj);
        } else if (sql.trim().toUpperCase().startsWith("UPDATE")) {
            sql = sql.replaceAll(" set ", " SET ")
                     .replaceAll(" Set ", " SET ")
                     .replaceAll(" SET ", " SET rev = rev+1, operate_time = NOW(), ");
            ReflectUtil.setFieldValue(boundSql, "sql", sql);
        } else if (sql.trim().toUpperCase().startsWith("SELECT")) {
            // 如果是查询请求, 切分where 拼装 userId信息
            StringBuffer sb = new StringBuffer();
            // 获取 tenant_id 租户值
            String tenant = TenantContextHolder.getTenant();
            if (sql.contains("where ") || sql.contains("WHERE ")) {
                // 只要包含where 的 语句, 全都拼上 需要的id信息
                String[] sqlWheres = sql.split("where|WHERE");
                for (int i = 0; i < sqlWheres.length; i++) {
                    if (i == 0) {
                        // 第一个不需要
                        sb.append(sqlWheres[i]);
                        continue;
                    }
                    String sqlEnd = sqlWheres[i];

                    sb.append(" where ").append(" tenant_id = ").append(tenant).append(" and ");
                    sb.append(sqlEnd);
                }
            }
            ReflectUtil.setFieldValue(boundSql, "sql", sb.toString());
        }
        return invocation.proceed();
    }

    // 借助Plugin的wrap方法来使用当前的拦截器包装我们的目标对象
    @Override
    public Object plugin(Object target) {
        // TODO Auto-generated method stub
        log.info("MysqlInterceptor plugin >>>>>>> {}", target);
        // 返回当前target创建的动态代理
        // 判断一下目标类型，如果是插件要拦截的对象时才执行Plugin.wrap方法，否则的话，直接返回目标本身
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        String dialect = properties.getProperty("dialect");
        log.info("mybatis intercept dialect  >>>>>>> {}", dialect);
    }
}