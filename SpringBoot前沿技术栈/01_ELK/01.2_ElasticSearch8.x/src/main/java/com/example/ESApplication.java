package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ESApplication {

	public static void main(String[] args) {
		SpringApplication.run(ESApplication.class, args);
	}

	// 注意：RestHighLevelClient 和 ElasticsearchTemplate 都已经在版本8.x.x被废弃
}
