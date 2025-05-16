package com.example;

import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// 批处理启动注解，要求放在配置类或者启动类上。SpringBoot会自动加载JobLauncher
@EnableBatchProcessing(
		dataSourceRef = "batchDataSource",
		transactionManagerRef = "batchTransactionManager"
)
public class JobControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobControlApplication.class, args);
	}

	@Resource
	private JobLauncher jobLauncher;
	@Resource
	private Job job1;
	@Resource
	private Job job2;

	// @Bean
	// public CommandLineRunner commandLineRunner1(){
	//     return args -> {
	//         JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("CurrentTime", LocalDateTime.now()).toJobParameters();
	//         jobLauncher.run(job2, jobParameters);
	//     };
	// }


	@Resource
	private Job job3;
	@Resource
	private Job job4;
	@Resource
	private Job job5;

	// TODO 区别以上的jobLauncher.run(),在于每次启动都是不一样的参数(也就是说每次启动都是不一样的作业)，因此会影响作业重启的demo
	@Bean
	public CommandLineRunner commandLineRunner2() {
		return args -> {
			jobLauncher.run(job3, new JobParameters());
		};
	}

}
