package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBatchValidateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchValidateApplication.class, args);
	}

	// JobLauncher - 作业启动器
	private final JobLauncher jobLauncher;

	// JobExplorer - 用于查找以前的作业参数信息的作业资源管理器
	private final JobExplorer jobExplorer;

	private final Job job1;
	private final Job job2;
	private final Job job3;
	private final Job job4;
	private final Job job5;
	private final Job job6;
	private final Job job7;
	private final Job job8;
	private final Job job9;

	// TODO 可以通过修改run()方法中的job值，来进行各个案例测试
	@Bean
	public CommandLineRunner commandLineRunner1() {
		return args -> {

			// 作业参数生成器 JobParametersBuilder()
			// 重构一个作业参数 key为"CurrentTime" value为'当前时间' 的生成器
			JobParameters jobParameters1 =
					new JobParametersBuilder()
							.addLocalDateTime("name", LocalDateTime.now())
							.toJobParameters();
			JobExecution execution1 = jobLauncher.run(job1, jobParameters1);
			Object currentTime = Objects.requireNonNull(execution1.getJobParameters().getParameter("name")).getValue();
			System.out.println("参数name值：" + currentTime);
			Long jobInstanceId1 = execution1.getJobId();
			System.out.println("该作业执行的工作实例Id：" + jobInstanceId1);


			System.out.println("=========================================================================");


			// 作业参数生成器 JobParametersBuilder(JobExplorer jobExplorer)
			// 重构一个能 查找以前对应的作业参数信息 的生成器
			JobParameters jobParameters2 =
					new JobParametersBuilder(jobExplorer)
							// 在这个作业参数生成器里 获取指定作业最后执行过的参数对象信息，并且依据增量参数逻辑，获取更新的参数
							.getNextJobParameters(job6)
							// 将 参数生成器对象 JobParametersBuilder 转换成 JobParameters 类型
							.toJobParameters();
			JobExecution execution2 = jobLauncher.run(job6, jobParameters2);
			Map<String, JobParameter<?>> parameters = execution2.getJobParameters().getParameters();
			System.out.println("作业参数信息: " + parameters);
			Long newJobInstanceId = execution2.getJobId();
			System.out.println("New JobInstanceId: " + newJobInstanceId);


			System.out.println("=========================================================================");


			jobLauncher.run(job9, jobParameters1);
		};
	}

}
