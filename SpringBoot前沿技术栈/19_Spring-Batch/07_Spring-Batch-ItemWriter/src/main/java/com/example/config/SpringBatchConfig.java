package com.example.config;

import com.example.dto.User;
import com.example.prepared.UserPreStatementSetter;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-16 08:51
 * @apiNote TODO SpringBatch配置类
 */
@Configuration
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class SpringBatchConfig {
    @Resource
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Resource
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器


    // 读取文件 user-validation.txt 数据
    @Bean
    public FlatFileItemReader<User> itemReader1(){
        return new FlatFileItemReaderBuilder<User>()
                .name("File Convert user-validation ItemReader")
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
    public FlatFileItemWriter<User> itemWriter1() {
        return new FlatFileItemWriterBuilder<User>()
                .name("userFlatItemWriter")
                // 输出位置
                .resource(new PathResource("/Users/example/IDEA/java-layout-path/SpringCloud以及前沿技术/25_Spring-Batch-ItemWriter/FlatFileItemWriter.txt"))
                .formatted()
                // 输出数据形式
                .format("id: %s, 姓名: %s, 年龄: %s")
                // 需要输出的属性
                .names("id", "name", "age")
                .build();
    }
    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job1() {
        return new JobBuilder("FlatFile Writer Job", jobRepository)
                .start(step1())
                .build();
    }

    // TODO 输出json文件
    @Bean
    public JsonFileItemWriter<User> itemWriter2() {
        return new JsonFileItemWriterBuilder<User>()
                .name("userJsonItemWriter")
                // 输出位置
                .resource(new PathResource("/Users/example/IDEA/java-layout-path/SpringCloud以及前沿技术/25_Spring-Batch-ItemWriter/JsonFileItemWriter.json"))
                // json对象调度器--将user对象缓存json格式，输出文档中
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .build();
    }
    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                .writer(itemWriter2())
                .build();
    }
    @Bean
    public Job job2() {
        return new JobBuilder("JsonFile Writer Job", jobRepository)
                .start(step2())
                .build();
    }

    // TODO 输出到数据库
    @Resource
    private DataSource dataSource;
    @Bean
    public JdbcBatchItemWriter<User> itemWriter3(){
        return new JdbcBatchItemWriterBuilder<User>()
                .dataSource(dataSource)
                .sql("insert into user(id, name, age) values(?, ?, ?)")
                // 设置sql中占位符参数
                .itemPreparedStatementSetter(new UserPreStatementSetter())
                .build();
    }
    @Bean
    public Step step3() {
        return new StepBuilder("step3", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                .writer(itemWriter3())
                .build();
    }
    @Bean
    public Job job3() {
        return new JobBuilder("DataSource Writer Job", jobRepository)
                .start(step3())
                .build();
    }

    // TODO 输出多终端
    //  上面几种输出方法都是一对一，真实开发可能没那么简单了，可能存在一对多，多个终端输出
    //  例如现在要求将 user-validation.txt 文件数据读取出来，输出到 FlatFileItemWriter.txt / JsonFileItemWriter.json / 数据库user表中
    @Bean
    public CompositeItemWriter<User> compositeItemWriter(){
        return new CompositeItemWriterBuilder<User>()
                .delegates(Arrays.asList(itemWriter1(), itemWriter2(), itemWriter3()))
                .build();
    }
    @Bean
    public Step step4() {
        return new StepBuilder("step4", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                .writer(compositeItemWriter())
                .build();
    }
    @Bean
    public Job job4() {
        return new JobBuilder("Composite Writer Job", jobRepository)
                .start(step4())
                .build();
    }
}
