package com.example.service.impl;

import com.example.service.TransactionService;
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
    public void saveUserTransaction() {
        jdbcTemplate.execute(
                "INSERT INTO `user`(`username`, `birthday`, `sex`, `address`) " +
                           "VALUES ('荒', '2024-05-27 11:44:00', '男', '雁塔区十年城')");
        // 执行以非事务方式定义的方法
        updateUserNonTransactional();
    }


    // NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。
    @Transactional(propagation = Propagation.NEVER)
    public void updateUserNonTransactional() {
        jdbcTemplate.update(
                "INSERT INTO `user` (`username`, `birthday`, `sex`, `address`) " +
                            "VALUES ('牛牛栏目', '2024-04-27 11:44:00', '女', '雁塔区兴贺佳苑')");
    }

    // Spring中的事务管理是基于AOP（面向切面编程）实现的，它通过动态代理来拦截方法调用并管理事务。
    // 如果在一个类的内部方法调用另一个需要事务管理的方法，而这个调用不是通过代理对象完成的，那么事务可能就不会生效。
    // 因此，如果插入操作是通过内部方法直接调用而不是通过外部接口（经过代理）调用的，事务可能就不会被正确管理，导致插入失败但ID自增。

    /**
     * Spring中的事务管理是基于AOP（面向切面编程）实现的，它通过动态代理来拦截方法调用并管理事务。
     * 疑问一：为什么事务方法 saveUserTransaction() 中执行非事务方式 test() 方法，应该抛出异常的却没有抛出异常？
     * 解答一：因为在执行 saveUserTransaction() 是通过代理对象执行，而执行 test() 方法是通过原始对象调用，而不是通过代理对象调用的。
     *        简单来说，在执行这两个方法时，并非同一个对象，因此事务是不生效的
     * 疑问二：为什么实际数据没有插入成功，但在数据库中 自增ID 却增加了？
     * 解答二：数据库的自增ID是在插入操作实际执行时立即生成并分配的，而不是在事务提交时。
     *        这意味着，一旦SQL插入语句被执行，无论事务最终是否成功提交，自增ID都会增加。
     *        这是因为自增ID的生成和分配是数据库的一个独立行为，与应用程序的事务管理机制是分开的。
     */

}
