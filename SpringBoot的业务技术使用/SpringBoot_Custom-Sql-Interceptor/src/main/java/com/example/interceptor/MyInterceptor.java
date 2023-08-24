package com.example.interceptor;

import cn.hutool.core.util.ReflectUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.util.Date;

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
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
})
@Configuration
public class MyInterceptor implements Interceptor {
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
        }
        return invocation.proceed();
    }

}