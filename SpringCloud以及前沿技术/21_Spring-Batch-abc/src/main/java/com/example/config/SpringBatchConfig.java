package com.example.config;

import com.example.incr.DailyTimesParametersIncrementer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-25 23:51
 * @apiNote TODO 批处理配置类
 */
@Configuration
public class SpringBatchConfig {

    @Autowired
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Autowired
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器


    // 批处理中的一部分任务
    @Bean
    // 这个注解表示的是在启动项目的时候不加载该Step步骤bean，等到step()被调用的时候才加载，称为延时获取
    // @StepScope
    public Tasklet tasklet(/*@Value("#{jobParameters['name']}") String name*/){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Spring Batch执行Job");

                // 获取批处理执行的参数方法一：
                Map<String, Object> parameters = chunkContext.getStepContext().getJobParameters();
                System.out.println(parameters.get("run.id"));

                // 获取批处理执行的参数方法二：
                // 需要加上@StepScope注解，并且给上相对应的 @Value("#{jobParameters['name']}" 赋值
                // System.out.println(name);

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


    // 4、作业增量参数--run.id自增
    /**
     * 我们的任务每执行一次，在数据库中会有相关的任务名称--(Spring Batch Job XXX)
     * 重新启动SpringBoot项目后，Spring Batch检索到有相同的任务，不会执行。甚至如果出现相同参数的相同任务，还会启动失败直接报错。
     * 因此，之前我们的批处理每执行一次后都要去改参数值或者改job的名称，甚至删除全部的表重新建，用以继续使用执行
     *
     * TODO 现在，我们使用 作业增量参数run.id 即可不再避免之前繁琐操作。
     * 原理：每一次作业都会有一个run.id的参数在其中，且每次这个参数都会进行一个自增的操作。换句话说，每次作业虽然job名一样，但其中的参数值不一样。
     */
    @Bean
    public Job job5(){
        return new JobBuilder("Spring Batch Job RunIdIncr", jobRepository)
                // 这里是官方提供的一个增量参数的实现类，如果我们想自己定义这个增量参数的细节(例如增量规则，增量参数key值等)，可以去实现接口 JobParametersIncrementer
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    // 5、作业增量参数--自定义时间戳
    @Bean
    public Job job6(){
        return new JobBuilder("Spring Batch Job DateIncr", jobRepository)
                .start(step())
                // 作业增量参数--自定义时间戳
                .incrementer(new DailyTimesParametersIncrementer())
                .build();
    }
}
