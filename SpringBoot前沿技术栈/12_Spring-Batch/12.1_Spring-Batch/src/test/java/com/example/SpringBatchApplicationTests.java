package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootTest
public class SpringBatchApplicationTests {

	@Autowired
	private JobRepository jobRepository;  // SpringBatch数据库的操作
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private PlatformTransactionManager batchTransactionManager;  // 事务管理器


	// 批处理中的一部分任务
	public Tasklet tasklet() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Spring Batch 执行 Task Test");
				// 每个步骤都会包含一个完整的执行状态。这个状态通过RepeatStatus来表示
				return RepeatStatus.FINISHED;
			}
		};
	}

	// 批处理中的顺序步骤
	public Step step() {
		return new StepBuilder("Spring Batch Step Test", jobRepository)
				.tasklet(tasklet(), batchTransactionManager)
				.build();
	}

	// 批处理作业
	public Job job() {
		return new JobBuilder("Spring Batch Job Test", jobRepository)
				.start(step())
				.build();
	}

	@Test
	void contextLoads() {
		try {
			jobLauncher.run(job(), new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			throw new RuntimeException(e);
		}
	}

}
