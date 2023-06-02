package com.example.config;

import com.example.dto.User1;
import com.example.dto.User2;
import com.example.fieldMapper.UserFieldMapper;
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


    /**
     * TODO 平面文件一般指的都是简单行/多行结构的纯文本文件，比如记事本记录文件。
     *   与XML这种区别在于没有结构，没有标签的限制。
     *   SpringBatch默认使用 FlatFileItemReader 实现平面文件的输入
     */
    @Bean
    public FlatFileItemReader<User1> itemReader1(){
        return new FlatFileItemReaderBuilder<User1>()
                .name("File Convert User1 ItemReader")
                // 获取文件
                .resource(new ClassPathResource("user1.txt"))
                // 解析数据 -- 指定解析其使用#分割 -- 默认是，逗号
                .delimited().delimiter("#")
                // 按照#截取数据之后，数据怎么命名
                .names("Id", "name", "age")
                // 封装数据 -- 将读取的数据封装到对象：User对象
                .targetType(User1.class) // 自动封装
                .build();
    }
    @Bean
    public ItemWriter<User1> itemWriter1() {
        return new ItemWriter<User1>() {
            @Override
            public void write(@NonNull Chunk<? extends User1> chunk) throws Exception {
                chunk.getItems().forEach(System.out::println);
            }
        };
    }
    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<User1, User1>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job1() {
        return new JobBuilder("Job Item Reader1", jobRepository)
                .start(step1())
                .build();
    }


    // TODO 平面文件数据进行手动封装(实现 FieldSetMapper 接口)
    @Bean
    public FlatFileItemReader<User2> itemReader2(){
        return new FlatFileItemReaderBuilder<User2>()
                .name("File Convert User2 ItemReader")
                // 获取文件
                .resource(new ClassPathResource("user2.txt"))
                // 解析数据 -- 指定解析其使用#分割 -- 默认是，逗号
                .delimited().delimiter("#")
                // 按照#截取数据之后，数据怎么命名
                .names("Id", "name", "age", "province", "city", "area")
                // 自定义封装数据
                .fieldSetMapper(new UserFieldMapper())
                .build();
    }
    @Bean
    public ItemWriter<User2> itemWriter2() {
        return new ItemWriter<User2>() {
            @Override
            public void write(@NonNull Chunk<? extends User2> chunk) throws Exception {
                chunk.getItems().forEach(System.out::println);
            }
        };
    }
    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .<User2, User2>chunk(1, batchTransactionManager)
                .reader(itemReader2())
                .writer(itemWriter2())
                .build();
    }
    @Bean
    public Job job2() {
        return new JobBuilder("Job Item Reader2", jobRepository)
                .start(step2())
                .build();
    }
}
