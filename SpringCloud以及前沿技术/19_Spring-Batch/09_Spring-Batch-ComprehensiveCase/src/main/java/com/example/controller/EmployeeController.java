package com.example.controller;

import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;


/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2023-06-27 23:55:14
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    private JobExplorer jobExplorer;

    @Resource
    @Qualifier("csvToDBJob")
    private Job csvToDBJob;

    @Resource
    @Qualifier("dbToDBJob")
    private Job dbToDBJob;

    // 生成csv文件数据
    @GetMapping("/dataInit")
    public ResponseEntity<String> dataInit() throws IOException {
        employeeService.dataInit();
        return ResponseEntity.ok().body("五十万数据重新生成成功!!! 🥸🥸🥸");
    }

    // 将csv文件数据添加到临时表 EmployeeTemp 中，返回执行工作的执行Id
    @GetMapping("/csvToDB")
    public String csvToDB() throws Exception {
        employeeService.truncateTemp(); // 清空数据运行多次执行

        // 需要多次执行，run.id 必须重写之前，再重构一个新的参数对象
        JobParameters jobParameters = new JobParametersBuilder(new JobParameters(), jobExplorer)
                .getNextJobParameters(csvToDBJob).toJobParameters();
        JobExecution run = jobLauncher.run(csvToDBJob, jobParameters);
        return run.getId().toString();
    }

    // 将临时表EmployeeTemp数据迁移到 Employee 表中
    @GetMapping("/dbToDB")
    public String dbToDB() throws Exception {
        employeeService.truncateAll();
        JobParameters jobParameters = new JobParametersBuilder(new JobParameters(),jobExplorer)
                .getNextJobParameters(dbToDBJob).toJobParameters();
        JobExecution run = jobLauncher.run(dbToDBJob, jobParameters);
        return run.getId().toString();
    }
}

