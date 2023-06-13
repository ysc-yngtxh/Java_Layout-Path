package com.example;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBatchValidateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchValidateApplication.class, args);
    }

    @Resource
    private JobLauncher jobLauncher;
    @Autowired
    private JobRegistry jobRegistry;
    @Autowired
    private JobExplorer jobExplorer;

    private final Job job1;
    private final Job job2;
    private final Job job3;
    private final Job job4;
    private final Job job5;
    private final Job job6;
    private final Job job7;
    private final Job job8;
    private final Job job9;

    // TODO 可以通过修改run()方法中的job值，来进行各个案例测试
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Job job = jobRegistry.getJob("Spring Batch Job RunIdIncr");
            // JobParameters jobParameters = job.getJobParametersIncrementer().getNext(jobExplorer.getLastJobExecution((JobInstance) job).getJobParameters());

            JobExecution jobExecution = jobLauncher.run(job6, new JobParameters()
                    // 添加作业参数
                    // new JobParameters( Map.of("name", new JobParameter<>("游诗成", String.class),
                    //         "age", new JobParameter<>("78fg9", String.class)) )
            );

            JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("CurrentTime", LocalDateTime.now()).toJobParameters();
            jobLauncher.run(job9, jobParameters);

            Long newRunId = jobExecution.getJobId();
            long previousRunId = newRunId - 1;

            System.out.println("New run id: " + newRunId);
            System.out.println("Previous run id: " + previousRunId);
        };
    }


}
