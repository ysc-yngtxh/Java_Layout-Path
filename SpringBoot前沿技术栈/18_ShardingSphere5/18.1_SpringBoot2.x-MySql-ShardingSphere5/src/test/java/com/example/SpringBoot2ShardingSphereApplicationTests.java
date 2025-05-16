package com.example;

import com.example.entity.EceUser;
import com.example.mapper.EceUserMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot2ShardingSphereApplicationTests {

	@Autowired
	private EceUserMapper eceUserMapper;

	@Test
	void contextLoads() {
		String data = "2024-09-16 10:00:00";
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(data, pattern);
		eceUserMapper.insert(
				EceUser.builder()
				       .eceId(123456)
				       .userCode("ECE-RPT")
				       .userName("李四")
				       .passWord("123456")
				       .email("123456@qq.com")
				       .phone("13888888888")
				       .birthday(localDateTime)
				       .age(22)
				       .sex("女")
				       .address("湖北武汉洪山区")
				       .status("ACTIVE")
				       .build()
		                    );
	}

	@Test
	void contextLoads2() {
		System.out.println(eceUserMapper.queryById(1));
	}
}
