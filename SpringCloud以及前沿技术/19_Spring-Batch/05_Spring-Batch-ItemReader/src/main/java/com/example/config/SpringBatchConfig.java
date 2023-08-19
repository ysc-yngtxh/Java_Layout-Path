package com.example.config;

import com.example.dto.User1;
import com.example.dto.User2;
import com.example.fieldMapper.UserFieldMapper;
import com.example.fieldMapper.UserRowMapper;
import com.example.listener.ErrorItemReaderListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
import org.springframework.batch.item.json.GsonJsonObjectReader;
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
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
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
                // 读取文件中的监听器设置在Step中
                .listener(new ErrorItemReaderListener())
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
    
    // TODO 将Json格式数据转为实体类User1
    @Bean
    public JsonItemReader<User1> itemReader3(){

        // 方式一：使用阿里jackson框架解析读取json
        JacksonJsonObjectReader<User1> jsonObjectReader = new JacksonJsonObjectReader<>(User1.class);
        ObjectMapper objectMapper = new ObjectMapper();
        jsonObjectReader.setMapper(objectMapper);

        // 方式二：使用谷歌Gson框架进行json解析读取
        GsonJsonObjectReader<User1> gsonJsonObjectReader = new GsonJsonObjectReader<>(User1.class);
        Gson gson = new Gson();
        gsonJsonObjectReader.setMapper(gson);

        return new JsonItemReaderBuilder<User1>()
                .name("userItemReader")
                .resource(new ClassPathResource("user3.json"))
                .jsonObjectReader(jsonObjectReader)
                // .jsonObjectReader(gsonJsonObjectReader)
                .build();
    }
    @Bean
    public Step step3() {
        return new StepBuilder("step3", jobRepository)
                .<User1, User1>chunk(1, batchTransactionManager)
                .reader(itemReader3())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job3() {
        return new JobBuilder("Job Item Reader3", jobRepository)
                .start(step3())
                .build();
    }

    // TODO 读数据库
    //  1、使用JDBC游标方式读取数据（就是将表数据从上往下一个一个的读取，类似于指针往下读）
    @Resource
    private DataSource dataSource;
    @Bean
    public JdbcCursorItemReader<User1> itemReader4(){

        return new JdbcCursorItemReaderBuilder<User1>()
                .name("userItemReader")
                // 连接数据库,spring容器自己实现
                .dataSource(dataSource)
                // 执行sql查询数据，将返回的数据以游标形式一条一条读
                .sql("select * from user where age < ? and name like ?")
                // 拼接参数
                .preparedStatementSetter(new ArgumentPreparedStatementSetter(new Object[]{20, "%敏%"}))
                // 数据库独处数据跟用户对象属性一一映射
                .rowMapper(new UserRowMapper())
                .build();
    }
    @Bean
    public Step step4() {
        return new StepBuilder("step4", jobRepository)
                .<User1, User1>chunk(1, batchTransactionManager)
                .reader(itemReader4())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job4() {
        return new JobBuilder("cursor-db-reader-job", jobRepository)
                .start(step4())
                .build();
    }

    // TODO 读数据库
    //  2、居于分页读取,这里的意思是读取数据库的方式是自定义的页数量来读取，每读一次就是一页（不是说查询最后读取出来呈现的分页效果哦）
    @Bean
    public PagingQueryProvider pagingQueryProvider(){
        SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();
        factoryBean.setDataSource(dataSource);
        // 查询语句
        factoryBean.setSelectClause("select *");
        // 查询表
        factoryBean.setFromClause("from user");
        // :age 表示占位符
        factoryBean.setWhereClause("where age < :age");
        // 排序
        factoryBean.setSortKey("id");
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Bean
    public JdbcPagingItemReader<User1> itemReader5(){

        return new JdbcPagingItemReaderBuilder<User1>()
                .name("userItemReader")
                // 连接数据库,spring容器自己实现
                .dataSource(dataSource)
                // 分页逻辑
                .queryProvider(pagingQueryProvider())
                // 条件
                .parameterValues(Map.of("age", 20))
                // 定义读取每页数据的条数
                .pageSize(10)
                // 数据库读取数据跟用户对象属性一一映射
                .rowMapper(new UserRowMapper())
                .build();
    }
    @Bean
    public Step step5() {
        return new StepBuilder("step5", jobRepository)
                .<User1, User1>chunk(1, batchTransactionManager)
                .reader(itemReader5())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job5() {
        return new JobBuilder("page-db-reader-job", jobRepository)
                .start(step5())
                .build();
    }
}
