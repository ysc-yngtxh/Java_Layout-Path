package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBatchValidateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchValidateApplication.class, args);
    }

    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;

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
    public CommandLineRunner commandLineRunner1() {
        return args -> {

            // job5,job6需要多次执行，run.id 必须在重写之前，重构一个新的参数对象给执行的run()方法里
            JobParameters newJobParameters =
                    // 新建一个空的参数建造者对象 JobParametersBuilder
                    new JobParametersBuilder(new JobParameters(), jobExplorer)
                            // 在这个空的参数建造者对象里获取工作job5最后执行过的参数对象信息，并且依据自增参数逻辑，获取更新的参数
                            .getNextJobParameters(job5)
                            // 将 参数建造者对象 JobParametersBuilder 转换成 JobParameters 类型
                            .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(job5, newJobParameters);

            Long newRunId = jobExecution.getJobId();
            long previousRunId = newRunId - 1;

            System.out.println("New run id: " + newRunId);
            System.out.println("Previous run id: " + previousRunId);

            // 重构一个当前毫秒时间的参数值去运行job
            JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("CurrentTime", LocalDateTime.now()).toJobParameters();
            jobLauncher.run(job9, jobParameters);

        };
    }


}
