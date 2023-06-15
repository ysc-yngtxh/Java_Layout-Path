package com.example.config;

import com.example.dto.User;
import com.example.processor.CustomizeItemProcessor;
import com.example.service.Impl.UserServiceImpl;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.ScriptItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.script.ScriptEngineManager;
import java.util.Arrays;

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


    // 读取文件 user-validation.txt 数据
    // TODO ValidatingItemProcessor: 校验处理器 (校验name值不为null和空值)
    //  BeanValidatingItemProcessor 是 ValidatingItemProcessor 子类
    //  核心 BeanValidatingItemProcessor 类是Spring Batch提供现成的Validator校验类，直接使用即可
    @Bean
    public FlatFileItemReader<User> itemReader1(){
        return new FlatFileItemReaderBuilder<User>()
                .name("File Convert user-validation ItemReader")
                // 获取文件
                .resource(new ClassPathResource("user-validation.txt"))
                // 解析数据 -- 指定解析其使用#分割 -- 默认是，逗号
                .delimited().delimiter("#")
                // 按照#截取数据之后，数据怎么命名
                .names("Id", "name", "age")
                // 封装数据 -- 将读取的数据封装到对象：User对象
                .targetType(User.class) // 自动封装
                .build();
    }
    // 校验参数是否合法，不合法则丢弃
    @Bean
    public BeanValidatingItemProcessor<User> beanValidatingItemProcessor(){
        BeanValidatingItemProcessor<User> itemProcessor = new BeanValidatingItemProcessor<>();
        itemProcessor.setFilter(true);
        return itemProcessor;
    }
    @Bean
    public ItemWriter<User> itemWriter1() {
        return new ItemWriter<User>() {
            @Override
            public void write(@NonNull Chunk<? extends User> chunk) throws Exception {
                chunk.getItems().forEach(System.out::println);
            }
        };
    }
    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                // 这里多了一步操作，以便引入ItemProcessor组件springboot
                .processor(beanValidatingItemProcessor())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job1() {
        return new JobBuilder("Validating Processor Job1", jobRepository)
                .start(step1())
                .build();
    }
}
