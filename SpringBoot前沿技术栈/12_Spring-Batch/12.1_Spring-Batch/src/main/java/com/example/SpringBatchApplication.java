package com.example;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 批处理启动注解，要求放在配置类或者启动类上。SpringBoot会自动加载JobLauncher
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
public class SpringBatchApplication {

	/**
	 * 需要注意的是：我们的任务每执行一次，所以在数据库中会有相关的任务名称--(Spring Batch Job)
	 * 重新启动SpringBoot项目后，Spring Batch检索到有相同的任务，不会执行。
	 * 因此，为了能够让我们的任务能够再次执行。需要我们把spring-batch数据库的所有表都删除掉然后重新生成
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

}
