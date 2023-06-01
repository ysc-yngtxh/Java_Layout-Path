package com.example.config;

import com.example.constant.ResourceCount;
import com.example.listener.CustomizeListener;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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


    // 模拟从数据库查询数据
    private int readCountDB = 100;

    // TODO 作业停止--方式一：使用监听器方式，不满足条件的直接返回停止状态
    @Bean
    public Tasklet tasklet1(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("------------step1------------");
                for (int i = 0; i < readCountDB; i++) {
                    ResourceCount.readCount ++;
                }
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Tasklet tasklet2(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("------------step2------------");
                System.err.println("readCount:" + ResourceCount.totalCount + "----totalCount:" + ResourceCount.totalCount);
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Step step1(){
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet1(), batchTransactionManager)
                // 配置 Step 监听器，方式一：实现StepExecutionListener接口
                .listener(new CustomizeListener())
                // 设置运行step可以被重新执行
                .allowStartIfComplete(true)
                .build();
    }
    @Bean
    public Step step2(){
        return new StepBuilder("step2", jobRepository)
                .tasklet(tasklet2(), batchTransactionManager)
                .build();
    }
    @Bean
    public Job job1(){
        return new JobBuilder("Job Stop1", jobRepository)
                .start(step1())
                // 当step1()返回的是STOPPED状态码，马上结束作业流程。并且重新启动step1()步骤
                .on("STOPPED").stopAndRestart(step1())
                .from(step1()).on("*").to(step2())
                .end()
                .build();
    }

    // TODO 作业停止--方式二：设置停止标记
    @Bean
    public Tasklet tasklet3(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("------------step3------------");
                for (int i = 0; i < readCountDB; i++) {
                    ResourceCount.readCount++;
                }
                // setTerminateOnly() 给运行中的stepExecution设置停止标记，Spring Batch 识别后直接停止步骤，进而停止流程
                if (ResourceCount.totalCount != ResourceCount.readCount) {
                    chunkContext.getStepContext().getStepExecution().setTerminateOnly();
                }
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Tasklet tasklet4(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("------------step4------------");
                System.err.println("readCount:" + ResourceCount.totalCount + "----totalCount:" + ResourceCount.totalCount);
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Step step3(){
        return new StepBuilder("step3", jobRepository)
                .tasklet(tasklet3(), batchTransactionManager)
                // 设置运行step可以被重新执行
                .allowStartIfComplete(true)
                .build();
    }
    @Bean
    public Step step4(){
        return new StepBuilder("step4", jobRepository)
                .tasklet(tasklet4(), batchTransactionManager)
                .build();
    }
    @Bean
    @Primary
    public Job job2(){
        return new JobBuilder("Job Stop2", jobRepository)
                .start(step3())
                .next(step4())
                .build();
    }
}
