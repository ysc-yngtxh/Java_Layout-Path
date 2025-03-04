package com.example.config;

import com.example.constant.ResourceCount;
import com.example.listener.CustomizeListener;
import jakarta.annotation.Resource;
import lombok.NonNull;
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
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-01 22:38
 * @apiNote TODO SpringBatch配置类
 */
@Configuration
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class SpringBatchConfig {
    @Resource
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Resource
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器

    // 模拟从数据库查询数据
    private final int readCountDB = 100;

    // TODO 作业停止--方式一：使用监听器方式，不满足条件的直接返回停止状态
    @Bean
    public Tasklet tasklet1() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("------------step1------------");
                for (int i = 0; i < readCountDB; i++) {
                    ResourceCount.readCount++;
                }
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Tasklet tasklet2() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("------------step2------------");
                System.err.println("readCount:" + ResourceCount.readCount + "----totalCount:" + ResourceCount.totalCount);
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet1(), batchTransactionManager)
                // 配置 Step 监听器，方式一：实现StepExecutionListener接口
                .listener(new CustomizeListener())
                // 设置运行step可以被重新执行--同Job无参数启动正常情况下该step1()步骤只会被执行一次，但是加上以下设置可以无限被执行
                .allowStartIfComplete(true)
                .build();
    }
    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .tasklet(tasklet2(), batchTransactionManager)
                .build();
    }
    @Bean
    public Job job1() {
        return new JobBuilder("Job Stop1", jobRepository)
                .start(step1())
                // 当step1()返回的是STOPPED状态码，马上结束作业流程。并且重新启动step1()步骤
                .on("STOPPED").stopAndRestart(step1())
                .from(step1()).on("*").to(step2())
                .end()
                .build();
    }


    // TODO 作业停止--方式二：设置停止标记（Tasklet中setTerminateOnly()方法）
    @Bean
    public Tasklet tasklet3() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("------------step3------------");
                for (int i = 0; i < readCountDB; i++) {
                    ResourceCount.readCount++;
                }
                // setTerminateOnly() 给运行中的stepExecution设置停止标记，Spring Batch 识别后直接停止步骤，进而停止流程
                if (ResourceCount.totalCount != ResourceCount.readCount) {
                    // 停止标记
                    chunkContext.getStepContext().getStepExecution().setTerminateOnly();
                }
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Step step3() {
        return new StepBuilder("step3", jobRepository)
                .tasklet(tasklet3(), batchTransactionManager)
                // 设置运行step可以被重新执行--同Job无参数启动正常情况下该step3()步骤只会被执行一次，但是加上以下设置可以无限被执行
                .allowStartIfComplete(true)
                .build();
    }
    @Bean
    public Step step4() {
        return new StepBuilder("step4", jobRepository)
                .tasklet(tasklet2(), batchTransactionManager)
                .build();
    }
    @Bean
    public Job job2() {
        return new JobBuilder("Job Stop2", jobRepository)
                .start(step3())
                .next(step4())
                .build();
    }


    // TODO 作业重启，表示允许作业步骤重新执行，默认情况下，只允许异常或终止状态的步骤重启。SpringBatch提供了3种重启控制操作
    /** TODO 1、无限重启（Step中allowStartIfComplete(true)方法）
     *   对于同一个Job作业和固定参数来说，只能够去执行一次。这是SpringBatch的固定逻辑，始终保持不变。
     *   但是对于某些特定状况下，需要我们重复执行。这个时候我们就可以使用Step步骤来设置
     */
    @Bean
    public Job job3() {
        return new JobBuilder("Job Stop3", jobRepository)
                .start(step3())
                .next(step4())
                .build();
    }


    // TODO 2、禁止重启（Job中preventRestart()方法）
    @Bean
    public Tasklet tasklet4() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("------------step5------------");

                // setTerminateOnly() 给运行中的stepExecution设置停止标记，Spring Batch 识别后直接停止步骤，进而停止流程
                chunkContext.getStepContext().getStepExecution().setTerminateOnly();

                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Tasklet tasklet5() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("------------step6------------");
                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Step step5() {
        return new StepBuilder("step5", jobRepository)
                .tasklet(tasklet4(), batchTransactionManager)
                .build();
    }
    @Bean
    public Step step6() {
        return new StepBuilder("step6", jobRepository)
                .tasklet(tasklet5(), batchTransactionManager)
                .build();
    }
    @Bean
    public Job job4() {
        return new JobBuilder("Job Stop4", jobRepository)
                // 禁止重启
                .preventRestart()
                .start(step5())
                .next(step6())
                .build();
    }



    // TODO 3、限制重启次数（Step中startLimit(2)方法）
    @Bean
    public Step step7() {
        return new StepBuilder("step7", jobRepository)
                // 限制重启次数，参数为设置的次数值
                .startLimit(2)
                .tasklet(tasklet4(), batchTransactionManager)
                .build();
    }
    @Bean
    public Step step8() {
        return new StepBuilder("step8", jobRepository)
                .tasklet(tasklet5(), batchTransactionManager)
                .build();
    }
    @Bean
    public Job job5() {
        return new JobBuilder("Job Stop5", jobRepository)
                .start(step7())
                .next(step8())
                .build();
    }
}
