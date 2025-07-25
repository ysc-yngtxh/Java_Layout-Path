package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TransactionManagerApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	@Transactional
	void contextLoads() {
		// execute：可以执行所有SQL语句，一般用于执行DDL语句。
		// update： 用于执行INSERT、UPDATE、DELETE等DML语句。
		// query：  用于DQL数据查询语句。
		jdbcTemplate.execute("INSERT INTO `db_student`(`name`, `email`, `age`) " +
						         "VALUES ('剑来', 'abcdefg.163.com', '28');"
		);
		throw new NullPointerException("耶耶耶！不好意思，洒家报错了～");
	}

}
