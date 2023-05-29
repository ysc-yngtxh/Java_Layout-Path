package com.example;

import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
// 批处理启动注解，要求放在配置类或者启动类上。SpringBoot会自动加载JobLauncher
@EnableBatchProcessing(
        dataSourceRef = "batchDataSource",
        transactionManagerRef = "batchTransactionManager"
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job job5;

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            jobLauncher.run(job5,
                    // 添加作业参数
                    new JobParameters());
        };
    }

}
