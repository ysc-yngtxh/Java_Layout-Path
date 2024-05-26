package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class TransactionManagerApplicationTests {

    // execute：可以执行所有SQL语句，一般用于执行DDL语句。
    // update：用于执行INSERT、UPDATE、DELETE等DML语句。
    // queryXxx：用于DQL数据查询语句。
    @Autowired
    private static JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        jdbcTemplate.execute(
                "CREATE TABLE `user` ( " +
                "    `id` INT NOT NULL AUTO_INCREMENT, " +
                "    `name` VARCHAR(45) NULL, " +
                "    `age` INT NULL, PRIMARY KEY (`id`)" +
                ")"
        );
    }

}
