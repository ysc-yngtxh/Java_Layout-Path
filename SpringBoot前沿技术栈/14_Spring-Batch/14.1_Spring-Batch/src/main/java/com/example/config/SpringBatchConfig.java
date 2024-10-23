package com.example.config;

import lombok.NonNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-25 23:51
 * @apiNote TODO 批处理配置类
 */
@Configuration
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class SpringBatchConfig {

    @Autowired
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Autowired
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器


    // 批处理中的一部分任务
    @Bean
    public Tasklet tasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("Spring Batch执行Job");
                // 每个步骤都会包含一个完整的执行状态。这个状态通过RepeatStatus来表示
                return RepeatStatus.FINISHED;
            }
        };
    }

    // 批处理中的顺序步骤
    @Bean
    public Step step(){
        return new StepBuilder("Spring Batch Step", jobRepository)
                .tasklet(tasklet(), batchTransactionManager)
                .build();
    }

    @Bean
    public Job job(){
        return new JobBuilder("Spring Batch Job5", jobRepository)
                .start(step())
                .build();
    }
}
