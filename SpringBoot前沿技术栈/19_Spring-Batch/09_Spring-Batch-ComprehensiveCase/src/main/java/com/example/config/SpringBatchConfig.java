package com.example.config;

import com.example.entity.Employee;
import com.example.listener.CsvToDBJobListener;
import com.example.partitioner.DBToDBPartitioner;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-30 07:26
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

    // TODO 工作一： 将csv文件数据读取到临时表EmployeeTemp中
    // 多线程读-读文件，使用FlatFileItemReader
    @Bean
    public FlatFileItemReader<Employee> cvsToDBItemReader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeCSVItemReader")
                .saveState(false) // 防止状态被覆盖
                .resource(new PathResource(new File(System.getProperty("user.dir") + path, "employee.csv").getAbsolutePath()))
                .delimited().delimiter(",")
                .names("id", "name", "age", "sex")
                .targetType(Employee.class)
                .build();
    }
    // 数据库写-使用mybatis提供批处理读入
    @Bean
    public MyBatisBatchItemWriter<Employee> cvsToDBItemWriter() {
        MyBatisBatchItemWriter<Employee> itemWriter = new MyBatisBatchItemWriter<>();
        itemWriter.setSqlSessionFactory(sqlSessionFactory); // 需要指定sqlSession工厂
        // 指定要操作sql语句的路径id：EmployeeDao.xml定义的sql语句id
        itemWriter.setStatementId("com.example.mapper.EmployeeDao.saveTemp");  // 操作sql
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


    public static int PAGESIZE = 1000; // mybatis分页读取数据，跟chunkSize 一样
    public static int RANGE = 10000;   // 每个分区读取数据范围(理解为个数)
    public static int GRIDSIZE = 50;   // 分区个数

    // TODO 工作二：读数据-从临时表 employee_temp 表数据读到 employee 表中
    @Bean
    @StepScope
    public MyBatisPagingItemReader<Employee> dBToDBJobItemReader(
            @Value("#{stepExecutionContext[from]}") Integer from,
            @Value("#{stepExecutionContext[to]}") Integer to,
            @Value("#{stepExecutionContext[range]}") Integer range){

        System.out.println("----------MyBatisPagingItemReader开始-----from: " + from + "  -----to:" + to + "  -----每片数量:" + range);
        MyBatisPagingItemReader<Employee> itemReader = new MyBatisPagingItemReader<Employee>();
        itemReader.setSqlSessionFactory(sqlSessionFactory);
        itemReader.setQueryId("com.example.mapper.EmployeeDao.selectTemp");
        // 通过分页来读取数据(还有通过游标来读取数据的方式)，表示每读取1000条数据为一页。
        itemReader.setPageSize(PAGESIZE);
        Map<String, Object> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        itemReader.setParameterValues(map);

        return itemReader;
    }
    // 数据库写- 写入到employee 表中
    @Bean
    public MyBatisBatchItemWriter<Employee> dbToDBItemWriter(){
        MyBatisBatchItemWriter<Employee> itemWriter = new MyBatisBatchItemWriter<>();
        itemWriter.setSqlSessionFactory(sqlSessionFactory);
        itemWriter.setStatementId("com.example.mapper.EmployeeDao.save");  // 操作sql
        return itemWriter;
    }
    // 文件分区处理器-处理分区
    @Bean
    public PartitionHandler dbToDBPartitionHandler() {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(GRIDSIZE);
        handler.setTaskExecutor(new SimpleAsyncTaskExecutor());
        handler.setStep(workStep());
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return handler;
    }
    // 每个从分区操作步骤
    @Bean
    public Step workStep() {
        return new StepBuilder("workStep", jobRepository)
                .<Employee, Employee>chunk(PAGESIZE, batchTransactionManager)
                .reader(dBToDBJobItemReader(null, null, null))
                .writer(dbToDBItemWriter())
                .build();
    }
    // 主分区操作步骤
    @Bean
    public Step masterStep() {
        return new StepBuilder("masterStep", jobRepository)
                .partitioner(workStep().getName(), new DBToDBPartitioner())
                .partitionHandler(dbToDBPartitionHandler())
                .build();
    }
    @Bean
    public Job dbToDBJob(){
        return new JobBuilder("dbToDB-step-job", jobRepository)
                .start(masterStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
