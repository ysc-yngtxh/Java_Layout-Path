package com.example.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.ArrayList;
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
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
)
public class ResultInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(ResultInterceptor.class);

    // 每一次 CRUD 操作都会经过mybatis拦截器，拦截器对象有以上四个，并且依次顺序执行
    // 每经过一个拦截器对象就会调用插件的plugin方法，也就是说该方法会调用4次。根据 @Intercepts 注解来决定是否进行拦截处理
    @Override
    public Object plugin(Object target) {
        log.info("ResultInterceptor Plugin >>>>>>> {}", target);
        // 判断一下目标类型，是本插件要拦截的对象时才执行Plugin.wrap方法，否则的话，直接返回目标本身
        // 我们要拦截的对象是定义在 @Signature 中的第一个参数 type 值
        if (target instanceof ResultSetHandler) {
            // 返回一个拦截器代理对象，这里的 this 就是我们的 ResultInterceptor 类，作为代理对象会去执行我们重写的拦截 intercept() 方法
            return Plugin.wrap(target, this);
        }
        // 返回目标对象，表示执行自己原有的内部拦截逻辑，也就不会去执行我们定义的 intercept(Invocation invocation) 方法
        return target;
    }
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("触发自定义的Mybatis拦截器 ResultSetHandler");

        // ResultSetHandler resultSetHandler1 = (ResultSetHandler) invocation.getTarget();
        // 通过java反射获得mappedStatement属性值
        // 可以获得mybatis里的 resultType
        Object result = invocation.proceed();
        if (result instanceof ArrayList<?> resultList) {
            for (Object oi : resultList) {
                Class<?> c = oi.getClass();
                Class<?>[] types = {Double.class};
                Method method = c.getMethod("setPrice", types);
                // 调用obj对象的 method 方法
                method.invoke(oi, 1000.0);
            }
        }
        return result;
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
