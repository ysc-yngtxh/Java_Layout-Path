package com.example.config;

import com.example.dto.User;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-17 18:04
 * @apiNote TODO SpringBatch配置类
 */
@Configuration
public class SpringBatchConfig {
    @Resource
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Resource
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器


    // TODO 多线程异步读取文件 user-validation.txt 数据
    //  可以在控制台打印中发现，打印的数据不再是有顺序的
    @Bean
    public FlatFileItemReader<User> itemReader1(){
        return new FlatFileItemReaderBuilder<User>()
                .name("File Convert user-validation ItemReader")
                // TODO 防止状态被覆盖
                //  SpringBatch提供大部分的ItemReader是有状态的，作业重启基本通过状态来确定作业停止位置，
                //  而在多线程环境中，如果对象维护状态被多个线程访问，可能存在线程件状态互相覆盖问题。
                //  所以设置为false表示关闭状态，但这也意味着作业不能重启了
                .saveState(false)
                // 获取文件
                .resource(new ClassPathResource("user-validation.txt"))
                // 解析数据 -- 指定解析其使用#分割 -- 默认是，逗号
                .delimited().delimiter("#")
                // 按照#截取数据之后，数据怎么命名
                .names("id", "name", "age")
                // 封装数据 -- 将读取的数据封装到对象：User对象
                .targetType(User.class) // 自动封装
                .build();
    }
    @Bean
    public ItemWriter<User> itemWriter1() {
        return new ItemWriter<User>() {
            @Override
            public void write(@NonNull Chunk<? extends User> chunk) throws Exception {
                chunk.forEach(System.out::println);
            }
        };
    }
    // TODO taskExecutor(new SimpleAsyncTaskExecutor())为作业步骤添加了多线程处理能力，
    //  以块为单位，一个快一个线程，观察上面的结果，很明显能看出输出的顺序是乱序的。
    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                .writer(itemWriter1())
                // TODO 多线程，任务执行器，每一个线程去执行chunk()方法中的块数据
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }
    @Bean
    public Job job1() {
        return new JobBuilder("Thread Step Job", jobRepository)
                .start(step1())
                .build();
    }
}
