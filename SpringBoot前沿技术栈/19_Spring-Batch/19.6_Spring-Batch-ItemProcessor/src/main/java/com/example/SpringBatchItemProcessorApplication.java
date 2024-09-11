package com.example;

import jakarta.annotation.Resource;
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
public class SpringBatchItemProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchItemProcessorApplication.class, args);
    }

    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job job1;
    @Resource
    private Job job2;
    @Resource
    private Job job3;
    @Resource
    private Job job4;
    @Resource
    private Job job5;

    @Bean
    public CommandLineRunner commandLineRunner1(){
        return args -> {
            JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("CurrentTime", LocalDateTime.now()).toJobParameters();
            jobLauncher.run(job5, jobParameters);
        };
    }
}
