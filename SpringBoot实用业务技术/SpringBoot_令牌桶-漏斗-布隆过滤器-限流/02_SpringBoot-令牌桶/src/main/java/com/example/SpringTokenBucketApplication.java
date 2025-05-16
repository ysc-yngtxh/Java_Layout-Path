package com.example;

import com.example.utils.BucketUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class SpringTokenBucketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTokenBucketApplication.class, args);
	}

	@PostConstruct
	public void initialize() {
		// 为了方便测试这里定义1容量  1增长速率
		BucketUtil bucketUtil = new BucketUtil(5, 1);
		// 生成名为：bucket的令牌桶
		BucketUtil.buckets.put("bucket", bucketUtil);
	}

	@Scheduled(fixedRate = 1000) // 定时1s
	public void timer() {
		if (BucketUtil.buckets.containsKey("bucket")) {
			// 名为：bucket的令牌桶 开始不断生成令牌
			BucketUtil.buckets.get("bucket").incrTokens();
		}
		System.out.println(BucketUtil.buckets.get("bucket").getSize());
	}
}
