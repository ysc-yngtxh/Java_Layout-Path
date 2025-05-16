package com.example.config;

import com.example.decider.CustomStatusDecider;
import com.example.listener.CustomStepListener;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-25 23:50
 * @apiNote TODO 批处理配置类
 */
@Configuration
// @SuppressWarnings("NullableProblems")  用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class SpringBatchConfig {

	@Resource
	private JobRepository jobRepository;  // SpringBatch数据库的操作

	@Resource
	private PlatformTransactionManager batchTransactionManager;  // 事务管理器

	private static int timer = 10;

	/**
	 * TODO
	 *  ItemReader会一直循环读，直到返回null才停止；
	 *  而processor也是一样，ItemReader读多少次，他就处理多少次；
	 *  ItemWriter一次性输出当前次输入的所有数据
	 */
	// 读操作
	@Bean
	public ItemReader<String> itemReader() {
		return new ItemReader<String>() {
			@Override
			public String read() {
				if (timer > 0) {
					System.out.println("========read=========");
					return "read-ret" + timer--;
				} else {
					return null;
				}
			}
		};
	}

	// 处理操作
	@Bean
	public ItemProcessor<String, String> itemProcessor() {
		return new ItemProcessor<String, String>() {
			@Override
			public String process(@NotNull String item) throws Exception {
				System.out.println("===========process==========" + item);
				return "process-ret->" + item;
			}
		};
	}

	// 处理操作
	@Bean
	public ItemWriter<String> itemWriter() {
		return new ItemWriter<String>() {
			@Override
			public void write(@NotNull Chunk<? extends String> chunk) throws Exception {
				System.out.println(chunk);
			}
		};
	}

	@Bean
	public Step step() {
		return new StepBuilder("step", jobRepository)
				/** TODO
				 *   这个chunk()中的第一个参数 chunkSize 表示的是每次先读三次，提交给processor处理三次，最后由writer输出三个值
				 *   timer=10，表示数据有10条，一个批次(趟)只能处理3条数据，需要4个批次(趟)来处理
				 */
				.<String, String>chunk(3, batchTransactionManager)
				.reader(itemReader())
				.processor(itemProcessor())
				.writer(itemWriter())
				// 配置 Step 监听器，方式一：实现StepExecutionListener接口
				.listener(new CustomStepListener())
				// 配置 Step 监听器，方式二：实现ChunkListener接口，区别于方式一，多了一个执行chunk失败的回调方法
				// .listener(new CustomChunkListener())
				.build();
	}

	@Bean
	public Job job1() {
		return new JobBuilder("Job Item", jobRepository)
				.start(step())
				.build();
	}

	/**
	 * TODO 步骤条件分支控制
	 */
	@Bean
	public Tasklet tasklet1() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
				System.out.println("开始执行步骤");
				// 这里模拟异常
				throw new RuntimeException("假装异常！！！");
				// return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Tasklet tasklet2() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
				System.out.println("执行成功步骤");
				return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Tasklet tasklet3() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
				System.out.println("执行失败步骤");
				return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Step executorStep() {
		return new StepBuilder("executorStep", jobRepository)
				.tasklet(tasklet1(), batchTransactionManager)
				.build();
	}

	@Bean
	public Step successStep() {
		return new StepBuilder("successStep", jobRepository)
				.tasklet(tasklet2(), batchTransactionManager)
				.build();
	}

	@Bean
	public Step failedStep() {
		return new StepBuilder("failedStep", jobRepository)
				.tasklet(tasklet3(), batchTransactionManager)
				.build();
	}

	/**
	 * TODO
	 *   1、on() 方法表示条件，上一个步骤返回值，匹配指定的字符串，满足后执行后续to步骤
	 *   2、* 为通配符，表示能匹配任意返回值
	 *   3、from() 表示从某个步骤开始进行条件判断
	 *   4、分支判断结束，流程以end方法结束，表示if/else逻辑结束
	 *   5、on() 方法中字符串取值于 ExitStatus 类常量(有五种类型的常量)，当然也可以自定义
	 */
	@Bean
	public Job job2() {
		return new JobBuilder("Step Condition Branch Control", jobRepository)
				.start(executorStep()).on("FAILED").to(failedStep())
				.from(executorStep()).on("*").to(successStep())
				.end()
				.build();
	}

	// TODO 自定义条件分支执行--决策器
	@Bean
	public CustomStatusDecider customStatusDecider() {
		return new CustomStatusDecider();
	}

	@Bean
	public Job job3() {
		return new JobBuilder("Custom Step Condition Branch Control", jobRepository)
				.start(executorStep())
				// 这里有一个坑点：就是不能使用new CustomStatusDecider()的方式
				// 原因在于：每一次new出来的都是不同对象，那么from就找不到相应执行过的步骤，从而无效
				.next(customStatusDecider())
				.from(customStatusDecider()).on("A").to(executorStep())
				.from(customStatusDecider()).on("B").to(successStep())
				.from(customStatusDecider()).on("*").to(failedStep())
				.end()
				.build();
	}

	// TODO SpringBatch的 BATCH_JOB_EXECUTION 表中会记录每一次工作执行完的状态。
	//  而我们可以通过自己的配置将 失败结束的步骤 直接转成其他状态的步骤
	@Bean
	public Job job4() {
		return new JobBuilder("Custom Step Failed Convert Status", jobRepository)
				.start(executorStep())
				// 表示将当前本应该是失败结束的步骤直接转成正常结束--COMPLETED
				//.on("FAILED").end()
				// 表示将当前本应该是失败结束的步骤直接转成失败结束：FAILED
				//.on("FAILED").fail()
				// 表示将当前本应该是失败结束的步骤直接转成停止结束：STOPPED。里面参数表示后续要重启时，从successStep位置开始
				.on("FAILED").stopAndRestart(successStep())
				.from(executorStep()).on("*").to(successStep())
				.end()
				.build();
	}

	// TODO 流式步骤--比如步骤StepB中会存在Step1、Step2、Step3
	//  使用FlowStep的好处在于，在处理复杂批处理逻辑中，flowStep可以单独实现一个子步骤流程，为批处理提供更高的灵活性
	@Bean
	public Tasklet taskletA() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
				System.out.println("------------StepA----------");
				return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Tasklet taskletB() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
				System.out.println("------------StepB----------");
				return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Tasklet taskletC() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
				System.out.println("------------StepC----------");
				return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Step stepA() {
		return new StepBuilder("stepA", jobRepository)
				.tasklet(taskletA(), batchTransactionManager)
				.build();
	}

	@Bean
	public Step stepB1() {
		return new StepBuilder("stepB1", jobRepository)
				.tasklet(taskletB(), batchTransactionManager)
				.build();
	}

	@Bean
	public Step stepB2() {
		return new StepBuilder("stepB2", jobRepository)
				.tasklet(taskletB(), batchTransactionManager)
				.build();
	}

	@Bean
	public Step stepB3() {
		return new StepBuilder("stepB3", jobRepository)
				.tasklet(taskletB(), batchTransactionManager)
				.build();
	}

	// 整合stepB1、stepB2、stepB3
	@Bean
	public Flow flow() {
		return new FlowBuilder<Flow>("流式步骤")
				.start(stepB1())
				.next(stepB2())
				.next(stepB3())
				.build();
	}

	// 将整合的Flow，交给步骤stepB
	@Bean
	public Step stepB() {
		return new StepBuilder("stepB", jobRepository)
				.flow(flow())
				.build();
	}

	@Bean
	public Step stepC() {
		return new StepBuilder("stepC", jobRepository)
				.tasklet(taskletC(), batchTransactionManager)
				.build();
	}

	@Bean
	public Job job5() {
		return new JobBuilder("Step Condition Branch Control", jobRepository)
				.start(stepA())
				// 我们可以发现next()方法只有两个重载方法，一个是Step类型参数，一个是JobExecutionDecider类型参数
				// 所以我们无法把flow()方法传进去。只能通过Step的flow()方法将flow转成Step
				.next(stepB())
				.next(stepC())
				.build();
	}

}
