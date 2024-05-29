package com.example.service.impl;

import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-29 22:42
 * @apiNote TODO
 */
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // Spring中的事务管理是基于AOP（面向切面编程）实现的，它通过动态代理来拦截方法调用并管理事务。
    // 如果在一个类的内部方法调用另一个需要事务管理的方法，而这个调用不是通过代理对象完成的，那么事务可能就不会生效。
    // 因此，如果插入操作是通过内部方法直接调用而不是通过外部接口（经过代理）调用的，事务可能就不会被正确管理，导致插入失败但ID自增。


    @Transactional
    public void saveUserTransaction() {
        jdbcTemplate.execute(
                "INSERT INTO `user`(`username`, `birthday`, `sex`, `address`) " +
                           "VALUES ('荒', '2024-05-27 11:44:00', '男', '雁塔区十年城')");
        updateUserNonTransactional();
        // Spring中的事务管理是基于AOP（面向切面编程）实现的，它通过动态代理来拦截方法调用并管理事务。
        // 这里就很奇怪：为什么当前事务下执行 test() 方法，按理来说抛出异常的却没有抛出异常？
        // 据库的自增ID是在插入操作实际执行时立即生成并分配的，而不是在事务提交时。
        // 这意味着，一旦SQL插入语句被执行，无论事务最终是否成功提交，自增ID都会增加。
        // 这是因为自增ID的生成和分配是数据库的一个独立行为，与应用程序的事务管理机制是分开的。
    }


    // NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。
    @Transactional(propagation = Propagation.NEVER)
    public void updateUserNonTransactional() {
        jdbcTemplate.update(
                "INSERT INTO `user` (`username`, `birthday`, `sex`, `address`) " +
                            "VALUES ('牛牛栏目', '2024-04-27 11:44:00', '女', '雁塔区兴贺佳苑')");
    }

}
