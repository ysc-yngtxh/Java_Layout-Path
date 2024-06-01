package com.example.service.impl;

import com.example.service.TransactionService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-29 22:42
 * @apiNote TODO
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveTransaction() {
        jdbcTemplate.execute(
                "INSERT INTO `user`(`username`, `birthday`, `sex`, `address`) " +
                           "VALUES ('荒', '2024-05-27 11:44:00', '男', '雁塔区十年城')");
        // 执行以非事务方式定义的方法
        updateNonTransactional();
    }


    // 事务传播级别 NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。
    @Transactional(propagation = Propagation.NEVER)
    public void updateNonTransactional() {
        jdbcTemplate.update(
                "INSERT INTO `user` (`username`, `birthday`, `sex`, `address`) " +
                            "VALUES ('牛牛栏目', '2024-04-27 11:44:00', '女', '雁塔区兴贺佳苑')");
    }


    /**
     * Spring中的事务管理是基于AOP（面向切面编程）实现的，它通过动态代理来拦截方法调用并管理事务。
     * 疑问：为什么事务方法 saveTransaction() 中调用传播级别为 NEVER 的 updateNonTransactional() 方法，
     *      按理来说，本应该抛出异常的，但实际上却没有抛出异常？并且插入数据还成功了，数据库没有进行回滚?
     * 解答：事务是基于 AOP 实现的，也就是说当我加上 @Transactional 注解，该方法已经被切面。
     *      此时我去调用 saveTransaction() 方法，实际上是通过代理对象调用的。
     *      但是具体的方法逻辑是通过代理对象中的 target(原始对象transactionService) 属性调用。
     *            public class proxy extends TransactionServiceImpl {
     *                private TransactionServiceImpl target;
     *                public void saveTransaction() {
     *                    // 在这里执行切面前置逻辑......
     *                    target.saveTransaction(); // 这里执行目标对象的方法
     *                    // 在这里执行切面后置逻辑......
     *                }
     *            }
     *      由此，在执行在执行 saveTransaction() 是通过代理对象执行，代理对象是能执行切面逻辑的，因此事务是能够生效的。
     *      而执行 updateNonTransactional() 方法是通过原始对象调用，原始对象无法进入到切面逻辑当中，所以事务会失效。
     */


    // 方法一：利用 AopContext.currentProxy() 解决事务失效问题
    @Transactional
    public void saveAopContextTransaction() {
        jdbcTemplate.execute(
                "INSERT INTO `user`(`username`, `birthday`, `sex`, `address`) " +
                        "VALUES ('荒', '2024-05-27 11:44:00', '男', '雁塔区十年城')");
        // 利用 Aop 特性暴露当前类，获取当前对象的AOP代理。通过代理执行其方法，肯定是会走事务注解的切面逻辑
        TransactionService transactionService = (TransactionService) AopContext.currentProxy();
        transactionService.updateNonTransactional();
    }


    // 方法二：将被调用的事务方法 updateNonTransactional() 择出来单独作为一个 Bean 注入到调用者所属类中。
    //        由于该方法添加了 @Transactional注解，所以会被Aop切面，最后在Spring容器中存在的 Bean 肯定是代理对象。
    //        而通过代理对象执行的方法，是可以顺利进入事务逻辑，从而解决事务失效问题。
    @Autowired
    private TransactionServiceTargetBean transactionServiceBean;

    @Transactional
    public void saveClassTransactional() {
        transactionServiceBean.updateNonTransactional();
    }
}
