
## Spring事务管理原理
    Spring中的事务管理是基于AOP（面向切面编程）实现的，它通过动态代理来拦截方法调用并管理事务。

## Spring如何实现异常回滚的

    在Spring中 TransactionInterceptor 和 PlatformTransactionManager 这两个类是整个事务模块的核心，
    TransactionInterceptor：负责拦截方法执行，进行判断是否需要提交或者回滚事务。
    PlatformTransactionManager：是Spring中的事务管理接口，真正定义了事务如何回滚和提交。

    当一个带有 @Transactional 注解的方法被调用时，TransactionInterceptor（事务拦截器）会在方法调用前后执行，
    负责事务的开启、提交或回滚。
    这发生在Spring的代理对象中，代理对象在运行时根据注解生成，以透明地处理事务逻辑。

### 1、TransactionInterceptor类中的代码有很多，我简化一下逻辑，方便说明：

```java
    // 以下代码省略部分内容
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 获取事务调用的目标方法
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        // 执行带事务调用
        return invokeWithinTransaction(invocation.getMethod(), targetClass, invocation::proceed);
    }
```


### 2、invokeWithinTransaction 简化逻辑如下：
```java
    // TransactionAspectSupport.class 省略了部分代码
    protected Object invokeWithinTransaction(Method method, @Nullable Class<?> targetClass
            , final InvocationCallback invocation) throws Throwable {
        Object retVal;
        try {
            // 调用真正的方法体
        	retVal = invocation.proceedWithInvocation();
        } catch (Throwable ex) {
        	// 如果出现异常，执行事务异常处理
        	completeTransactionAfterThrowing(txInfo, ex);
        	throw ex;
        } finally {
            // 最后做一下清理工作，主要是缓存和状态等
        	cleanupTransactionInfo(txInfo);
        }
        // 如果没有异常，直接提交事务。
        commitTransactionAfterReturning(txInfo);
        return retVal;
	}
```


### 3、事务出现异常回滚的逻辑completeTransactionAfterThrowing如下：
```java
    // 省略部分代码
    protected void completeTransactionAfterThrowing(@Nullable TransactionInfo txInfo, Throwable ex) {
        // 判断是否需要回滚，判断的逻辑就是看有没有声明事务属性，同时判断是不是在目前的这个异常中执行回滚。
        if (txInfo.transactionAttribute != null && txInfo.transactionAttribute.rollbackOn(ex)) {
            // 执行回滚
            txInfo.getTransactionManager().rollback(txInfo.getTransactionStatus());
        } else {
            // 否则不需要回滚，直接提交即可。
            txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
        }
    }
```


### 4、上面的代码已经把Spring的事务的基本原理说清楚了，如何进行判断执行事务，如何回滚。
###    下面到了真正执行回滚逻辑的代码中 PlatformTransactionManager 接口的子类，
###    我们以JDBC的事务为例，DataSourceTransactionManager 就是jdbc的事务管理类。
###    跟踪上面的代码 rollback(txInfo.getTransactionStatus()) 可以发现最终执行的代码如下：
```java
    @Override
    protected void doRollback(DefaultTransactionStatus status) {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection con = txObject.getConnectionHolder().getConnection();
        if (status.isDebug()) {
            logger.debug("Rolling back JDBC transaction on Connection [" + con + "]");
        }
        try {
            // 调用jdbc的 rollback进行回滚事务。
            con.rollback();
        } catch (SQLException ex) {
            throw new TransactionSystemException("Could not roll back JDBC transaction", ex);
        }
    }
```

## 小结
    Spring 主要依靠 TransactionInterceptor 来拦截执行方法体，判断是否开启事务，
    然后执行事务方法体，方法体中 catch 住异常,接着判断是否需要回滚，如果需要回滚就委托真正的 TransactionManager
    比如JDBC中的 DataSourceTransactionManager 来执行回滚逻辑。提交事务也是同样的道理。