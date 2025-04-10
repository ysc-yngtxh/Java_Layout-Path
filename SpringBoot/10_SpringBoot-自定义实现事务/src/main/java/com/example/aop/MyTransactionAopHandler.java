package com.example.aop;

import com.example.annotation.CustomTransaction;
import com.example.config.DataSourceConnectHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * @description:
 * @author: 游家纨绔
 * @create: 2020-03-29 17:08
 **/
@Aspect
@Component
public class MyTransactionAopHandler {

    @Autowired
    DataSourceConnectHolder connectHolder;

    // 异常类型
    Class<? extends Throwable>[] exception;

    // 拦截所有 @CustomTransaction 注解的方法
    @Around("@annotation(com.example.annotation.CustomTransaction)")
    public Object TransactionProceed(ProceedingJoinPoint proceed) throws Throwable {
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) proceed.getSignature();
        Method method = methodSignature.getMethod();
        if (method == null) {
            return result;
        }
        CustomTransaction transaction = method.getAnnotation(CustomTransaction.class);
        if (transaction != null) {
            // 当存在自定义事务注解，获取事务回滚的异常类型
            exception = transaction.rollbackFor();
        }
        try {
            result = proceed.proceed();
        } catch (Throwable throwable) {
            // 异常处理
            completeTransactionAfterThrowing(throwable);
            throw throwable;
        }
        // 执行目标方法，没有捕获到异常就直接提交
        doCommit();
        return result;
    }

    /**
     * 异常处理，捕获的异常是目标异常或者其子类，就进行回滚，否则就提交事务。
     */
    private void completeTransactionAfterThrowing(Throwable throwable) {
        if (exception != null && exception.length > 0) {
            for (Class<? extends Throwable> e : exception) {
                if (e.isAssignableFrom(throwable.getClass())) {
                    doRollBack();
                }
            }
        }
        doCommit();
    }

    /**
     * 执行回滚，最后关闭连接和清理线程绑定
     */
    private void doRollBack() {
        try {
            connectHolder.getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectHolder.cleanHolder();
        }
    }

    /**
     * 执行提交，最后关闭连接和清理线程绑定
     */
    private void doCommit() {
        try {
            connectHolder.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectHolder.cleanHolder();
        }
    }
}