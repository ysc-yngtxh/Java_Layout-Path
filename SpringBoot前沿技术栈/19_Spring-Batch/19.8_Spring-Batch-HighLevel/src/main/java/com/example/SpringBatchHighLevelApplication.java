package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
// 批处理启动注解，要求放在配置类或者启动类上。SpringBoot会自动加载JobLauncher
@EnableBatchProcessing(
		dataSourceRef = "batchDataSource",
		transactionManagerRef = "batchTransactionManager"
)
@RequiredArgsConstructor
public class SpringBatchHighLevelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchHighLevelApplication.class, args);
	}

	private final JobLauncher jobLauncher;

	private final Job job1;
	private final Job job2;
	private final Job job3;

	@Bean
	public CommandLineRunner commandLineRunner1(){
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("CurrentTime", LocalDateTime.now()).toJobParameters();
			jobLauncher.run(job3, jobParameters);
		};
	}
}
