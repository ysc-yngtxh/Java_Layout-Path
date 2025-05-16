package com.example.config;

import com.example.dto.User;
import com.example.handler.UserPartitioner;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import lombok.NonNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-17 18:00
 * @apiNote TODO SpringBatch配置类
 */
@Configuration
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class SpringBatchConfig {

	@Resource
	private JobRepository jobRepository;  // SpringBatch数据库的操作

	@Resource
	private PlatformTransactionManager batchTransactionManager;  // 事务管理器


	// TODO 多线程异步读取文件 user-validation.txt 数据
	//  可以在控制台打印中发现，打印的数据不再是有顺序的
	@Bean
	public FlatFileItemReader<User> itemReader1() {
		return new FlatFileItemReaderBuilder<User>()
				.name("File Convert user-validation ItemReader")
				// TODO 防止状态被覆盖
				//  SpringBatch提供大部分的ItemReader是有状态的，作业重启基本通过状态来确定作业停止位置，
				//  而在多线程环境中，如果对象维护状态被多个线程访问，可能存在线程件状态互相覆盖问题。
				//  所以设置为false表示关闭状态，但这也意味着作业不能重启了
				.saveState(false)
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
	public ItemWriter<User> itemWriter1() {
		return new ItemWriter<User>() {
			@Override
			public void write(@NonNull Chunk<? extends User> chunk) throws Exception {
				chunk.forEach(System.out::println);
			}
		};
	}

	//  taskExecutor(new SimpleAsyncTaskExecutor())为作业步骤添加了多线程处理能力，
	//  以块为单位，一个块一个线程，观察上面的结果，很明显能看出输出的顺序是乱序的。
	@Bean
	public Step step1() {
		return new StepBuilder("step1", jobRepository)
				.<User, User>chunk(2, batchTransactionManager)
				.reader(itemReader1())
				.writer(itemWriter1())
				// TODO 多线程，任务执行器，每一个线程去执行chunk()方法中的块数据
				.taskExecutor(new SimpleAsyncTaskExecutor())
				.build();
	}

	@Bean
	public Job job1() {
		return new JobBuilder("Thread Step Job", jobRepository)
				.start(step1())
				.build();
	}

	// TODO 并行步骤
	//  需要指定并行的flow步骤，先是 flowParallel 然后是 jsonParallel ，
	//  两个步骤间使用 .split(new SimpleAsyncTaskExecutor()) 隔开，表示线程池开启2个线程，
	//  分别处理 flowParallel， jsonParallel 两个步骤。
	@Bean
	public JsonItemReader<User> itemReader2() {
		// 使用阿里jackson框架解析读取json
		JacksonJsonObjectReader<User> jsonObjectReader = new JacksonJsonObjectReader<>(User.class);
		ObjectMapper objectMapper = new ObjectMapper();
		jsonObjectReader.setMapper(objectMapper);

		return new JsonItemReaderBuilder<User>()
				.name("JsonItem Reader")
				.resource(new ClassPathResource("JsonItemReader.json"))
				.jsonObjectReader(jsonObjectReader)
				.build();
	}

	@Bean
	public Step step2() {
		return new StepBuilder("step2", jobRepository)
				.<User, User>chunk(2, batchTransactionManager)
				.reader(itemReader2())
				.writer(itemWriter1())
				.build();
	}

	// TODO 这种方式与Step中的flow()方法是有区别的(这种方式是流式步骤，同步运行)
	//  现在我们这种方式是异步进行，多线程的
	@Bean
	public Job job2() {
		// 并行线程1: flat文件读取
		Flow flowParallel = new FlowBuilder<Flow>("flowParallel1")
				.start(step1())
				.build();

		// 并行线程2: json文件读取
		Flow jsonParallel = new FlowBuilder<Flow>("jsonParallel2")
				.start(step2())
				// 开启线程执行步骤
				.split(new SimpleAsyncTaskExecutor())
				// 将 并行步骤1 添加到 并行步骤2 ，这样子步骤同时启动
				.add(flowParallel)
				.build();

		return new JobBuilder("Parallel Step Job", jobRepository)
				.start(jsonParallel)
				// 因为是流，需要end()方法结束
				.end()
				.build();
	}


	// TODO 分区步骤
	// 编写从步骤--工作步骤--分区步骤--读操作
	@Bean
	@StepScope
	// 多个从步骤共有同个itemReader 组件，不能写死操作文件资源，需要使用变量方式动态指定
	// @Value("#{stepExecutionContext['file']}") 从步骤的上下文路径中获取要读的资源文件对象
	// stepExecutionContext['****'] 是固定写法。里面的file是自定义的属性名称
	// 能实现的前提：步骤上下文中必须要有值，这些数据值是在主步骤的分区器中设置。
	public FlatFileItemReader<User> itemReader3(@Value("#{stepExecutionContext['file']}") org.springframework.core.io.Resource resource) {
		return new FlatFileItemReaderBuilder<User>()
				.name("Partition Step ItemReader")
				// TODO 动态获取文件
				.resource(resource)
				// 解析数据
				.delimited()
				// 指定解析其使用#分割 -- 默认是，逗号
				.delimiter("#")
				// 按照#截取数据之后，数据怎么命名
				.names("id", "name", "age")
				// 封装数据 -- 将读取的数据封装到对象：User对象
				.targetType(User.class) // 自动封装
				.build();
	}

	// 文件分区处理器-处理分区
	@Bean
	public PartitionHandler userPartitionHandler() {
		TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
		handler.setGridSize(5);
		handler.setTaskExecutor(new SimpleAsyncTaskExecutor());
		handler.setStep(step3());
		try {
			handler.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler;
	}

	// 编写从步骤
	@Bean
	public Step step3() {
		return new StepBuilder("step3", jobRepository)
				.<User, User>chunk(2, batchTransactionManager)
				.reader(itemReader3(null))
				.writer(itemWriter1())
				.build();
	}

	// 主分区操作步骤
	@Bean
	public Step masterStep() {
		return new StepBuilder("masterStep", jobRepository)
				.partitioner(step3().getName(), new UserPartitioner())
				.partitionHandler(userPartitionHandler())
				.build();
	}

	@Bean
	@Primary
	public Job job3() {
		return new JobBuilder("job3", jobRepository)
				.start(masterStep())
				.build();
	}
}
