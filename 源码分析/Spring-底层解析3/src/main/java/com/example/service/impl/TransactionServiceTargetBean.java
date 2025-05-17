package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 17:20:00
 * @apiNote TODO
 */
@Service
public class TransactionServiceTargetBean {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 事务传播级别 NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。
	@Transactional(propagation = Propagation.NEVER)
	public void updateNonTransactional() {
		jdbcTemplate.update(
				"INSERT INTO `user` (`username`, `birthday`, `sex`, `address`) " +
						"VALUES ('牛牛栏目', '2024-04-27 11:44:00', '女', '雁塔区兴贺佳苑')");
	}

}
