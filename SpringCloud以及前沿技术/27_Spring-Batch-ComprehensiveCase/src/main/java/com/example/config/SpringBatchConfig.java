package com.example.config;

import com.example.entity.Employee;
import com.example.listener.CsvToDBJobListener;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;

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

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Value("${job.data.path}")
    private String path;

    // 多线程读-读文件，使用FlatFileItemReader
    @Bean
    public FlatFileItemReader<Employee> cvsToDBItemReader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeCSVItemReader")
                .saveState(false) // 防止状态被覆盖
                .resource(new PathResource(new File(path, "employee.csv").getAbsolutePath()))
                .delimited()
                .names("id", "name", "age", "sex")
                .targetType(Employee.class)
                .build();
    }

    // 数据库写-使用mybatis提供批处理读入
    @Bean
    public MyBatisBatchItemWriter<Employee> cvsToDBItemWriter() {
        MyBatisBatchItemWriter<Employee> itemWriter = new MyBatisBatchItemWriter<>();
        itemWriter.setSqlSessionFactory(sqlSessionFactory); // 需要指定sqlSession工厂
        // 指定要操作sql语句，路径id为：EmployeeMapper.xml定义的sql语句id
        itemWriter.setStatementId("com.example.dao.EmployeeDao.saveTemp");  // 操作sql
        return itemWriter;
    }

    @Bean
    public Step csvToDBStep() {
        return new StepBuilder("csvToDBStep", jobRepository)
                .<Employee, Employee>chunk(10000, batchTransactionManager)  // 每个块10000个 共50个
                .reader(cvsToDBItemReader())
                .writer(cvsToDBItemWriter())
                .taskExecutor(new SimpleAsyncTaskExecutor())  // 多线程读写
                .build();

    }

    @Bean
    public Job csvToDBJob() {
        return new JobBuilder("csvToDB-step-job", jobRepository)
                .start(csvToDBStep())
                .incrementer(new RunIdIncrementer()) // 保证可以多次执行
                .listener(new CsvToDBJobListener())
                .build();
    }

}
