package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
public class MapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapperApplication.class, args);
	}

}
