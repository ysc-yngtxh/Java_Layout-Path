package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
// 批处理启动注解，要求放在配置类或者启动类上。SpringBoot会自动加载JobLauncher
@EnableBatchProcessing(
        dataSourceRef = "batchDataSource",
        transactionManagerRef = "batchTransactionManager"
)
public class SpringBatchCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchCaseApplication.class, args);
    }

    /**
     * 案例需求
     * 1> 先动态生成50w条员工数据，存放在employee.csv文件中
     * 2> 启动作业异步读取employee.csv文件，将读到数据写入到employee_temp表，要求记录读与写消耗时间
     * 3> 使用分区的方式将employee_temp表的数据读取并写入到employee表

     * 分析
     *  上面需求存在一定连贯性，为了操作简单，使用springMVC项目， 每一个需求对应一个接口：
     *  1：发起请求接口为 '/dataInit' 初始化50w数据进入employee.csv文件
     *     使用技术点：SpringMVC IO
     *  2：发起请求接口为 '/csvToDB' 启动作业，将employee.csv 数据写入employee_temp表, 记录读与写消耗时间
     *     使用技术点：SpringMVC ItemReader JobExecutionListener
     *     ItemWriter (如果使用Mybatis框架MyBatisBatchItemWriter/MyBatisPagingItemReaderReader)
     *  3：发起请求接口为 '/dbToDB' 启动作业，将employee_temp数据写入employee表
     *     使用技术点：SpringMVC ItemReader partitioner
     *     ItemWriter(如果使用Mybatis框架：MyBatisBatchItemWriter/MyBatisPagingItemReaderReader)
     */

}
