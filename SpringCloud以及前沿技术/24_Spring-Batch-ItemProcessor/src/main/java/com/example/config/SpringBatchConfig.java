package com.example.config;

import com.example.dto.User1;
import com.example.dto.User2;
import com.example.fieldMapper.UserFieldMapper;
import com.example.fieldMapper.UserRowMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

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
}
