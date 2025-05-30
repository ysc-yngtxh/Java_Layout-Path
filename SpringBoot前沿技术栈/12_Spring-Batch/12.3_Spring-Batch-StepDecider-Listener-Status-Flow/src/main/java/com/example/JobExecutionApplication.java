package com.example;

import jakarta.annotation.Resource;
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
public class JobExecutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobExecutionApplication.class, args);
	}

	@Resource
	private JobLauncher jobLauncher;

	private final Job job1;
	private final Job job2;
	private final Job job3;
	private final Job job4;
	private final Job job5;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			JobParameters jobParameters =
					new JobParametersBuilder()
							.addLocalDateTime("CurrentTime", LocalDateTime.now())
							.toJobParameters();
			jobLauncher.run(job5, jobParameters);
		};
	}

}
