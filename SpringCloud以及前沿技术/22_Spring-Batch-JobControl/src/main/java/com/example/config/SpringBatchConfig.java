package com.example.config;

import com.example.listener.CustomizeListener;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-01 22:38
 * @apiNote TODO SpringBatch配置类
 */
@Configuration
public class SpringBatchConfig {
    @Resource
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Resource
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器


    @Bean
    public Tasklet tasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                return null;
            }
        };
    }
    @Bean
    public Step step(){
        return new StepBuilder("step", jobRepository)
                .tasklet(tasklet(), batchTransactionManager)
                // 配置 Step 监听器，方式一：实现StepExecutionListener接口
                .listener(new CustomizeListener())
                .build();
    }
    @Bean
    public Job job1(){
        return new JobBuilder("Job Item", jobRepository)
                .start(step())
                .build();
    }
}
