package com.example.config;

import com.example.incr.DailyTimesParametersIncrementer;
import com.example.listener.CustomAnnoJobExecutionListener;
import com.example.listener.CustomInterfaceJobExecutionListener;
import com.example.validate.CustomValidate;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-25 23:51
 * @apiNote TODO 批处理配置类
 */
@Configuration
// 批处理启动注解，要求放在配置类或者启动类上。SpringBoot会自动加载JobLauncher
@EnableBatchProcessing(
        dataSourceRef = "batchDataSource",
        transactionManagerRef = "batchTransactionManager"
)
public class SpringBatchConfig {

    @Resource
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Resource
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器


    // 批处理中的一部分任务
    @Bean
    // 这个注解表示的是在启动项目的时候不加载该Step步骤bean，等到step()被调用的时候才加载，称为延时获取
    // @StepScope
    public Tasklet tasklet(/*@Value("#{jobParameters['name']}") String name*/){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {

                System.out.println("Spring Batch 执行 Job");

                // 获取批处理的执行中的状态
                JobExecution jobExecution = contribution.getStepExecution().getJobExecution();
                System.err.println("作业执行中的状态" + jobExecution.getStatus());

                // 获取批处理执行的参数方法一：
                Map<String, Object> parameters = chunkContext.getStepContext().getJobParameters();
                System.out.println(parameters.get("run.id"));

                // 获取批处理执行的参数方法二：
                // 需要加上@StepScope注解，并且给上相对应的 @Value("#{jobParameters['name']}" 赋值
                // 且 jobParameters['****'] 为固定写法，里面的属性名为传参时的属性名
                // System.out.println(name);

                // 每个步骤都会包含一个完整的执行状态。这个状态通过RepeatStatus来表示
                return RepeatStatus.FINISHED;
            }
        };
    }

    // 批处理中的顺序步骤
    @Bean
    public Step step(){
        return new StepBuilder("Spring Batch Step", jobRepository)
                .tasklet(tasklet(), batchTransactionManager)
                .build();
    }

    // 1、自定义的参数校验器
    @Bean
    public CustomValidate customValidate(){
        return new CustomValidate();
    }

    // 2、默认的参数校验器
    @Bean
    public DefaultJobParametersValidator defaultJobParametersValidator(){
        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator();
        // 必传参数
        defaultJobParametersValidator.setRequiredKeys(new String[]{"name"});  // 必须传name参数,否则报错
        // 可选参数
        defaultJobParametersValidator.setOptionalKeys(new String[]{"age"});   // age是可选参数
        return defaultJobParametersValidator;
    }

    // 3、组合参数检验器
    @Bean
    public CompositeJobParametersValidator compositeJobParametersValidator() throws Exception {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        validator.setValidators(Arrays.asList(customValidate(), defaultJobParametersValidator()));
        validator.afterPropertiesSet();
        return validator;
    }

    // 1、指定自定义参数校验器
    @Bean
    public Job job1(){
        return new JobBuilder("Spring Batch Job CustomValidate", jobRepository)
                .start(step())
                .validator(customValidate())  // 指定自定义参数校验器
                .build();
    }

    // 2、指定默认参数校验器
    @Bean
    public Job job2(){
        return new JobBuilder("Spring Batch Job DefaultValidate", jobRepository)
                .start(step())
                .validator(defaultJobParametersValidator())  // 指定默认参数校验器
                .build();
    }

    /**
     * 这里有一个注意点，当同时存在自定义校验和默认校验时，程序会执行默认的校验器，即 DefaultJobParametersValidator
     * 即先抛出 The JobParameters do not contain required keys: [name] 的异常
     * 但是不会去执行自定义的参数校验器 customValidate()。
     * 我这里的理解就是：你要想使用自定义就干脆直接都在自定义里去做校验啊。你都选择默认的啦，干嘛还用自定义的啊？所以自定义的校验器当然无效啦
     * 扩展：这里我想到了一种可能。就是如果我有两个乃至以上的自定义校验器，这个时候是都执行还是只执行一个亦或者是根据什么规则来选择执行？
     *       我并没有去写个Demo证实一下，遗留给感兴趣的小伙伴们😛😝😜🤪
     */
    @Bean
    public Job job3() throws Exception {
        return new JobBuilder("Spring Batch Job CustomAndDefaultValidate", jobRepository)
                .start(step())
                .validator(customValidate())  // 指定自定义参数校验器
                .validator(defaultJobParametersValidator())  // 指定默认参数校验器
                .build();
    }

    // 3、指定组合参数校验器
    /**
     * 当我们使用组合校验器时，程序的校验顺序则是按照校验器中 setValidators() 方法的集合顺序来的
     * 这里我们 Arrays.asList(customValidate(), defaultJobParametersValidator()) 可以看到顺序是自定义校验器排在前面
     * 所以，先抛出的异常是：批处理name参数不能为null或者空
     */
    @Bean
    public Job job4() throws Exception {
        return new JobBuilder("Spring Batch Job MergeValidate", jobRepository)
                .start(step())
                .validator(compositeJobParametersValidator())  // 指定组合参数校验器
                .build();
    }

    // 4、作业增量参数--run.id自增
    /**
     * 我们的任务每执行一次，在数据库中会有相关的任务名称--(Spring Batch Job XXX)
     * 重新启动SpringBoot项目后，Spring Batch检索到有相同的任务，不会执行。甚至如果出现相同参数的相同任务，还会启动失败直接报错。
     * 因此，之前我们的批处理每执行一次后都要去改参数值或者改job的名称，甚至删除全部的表重新建，用以继续使用执行
     *
     * TODO 现在，我们使用 作业增量参数run.id 即可不再避免之前繁琐操作。
     * 原理：每一次作业都会有一个run.id的参数在其中，且每次这个参数都会进行一个自增的操作。换句话说，每次作业虽然job名一样，但其中的参数值不一样。
     */
    @Bean
    public Job job5(){
        return new JobBuilder("Spring Batch Job RunIdIncr", jobRepository)
                // 这里是官方提供的一个增量参数的实现类，如果我们想自己定义这个增量参数的细节(例如增量规则，增量参数key值等)，可以去实现接口 JobParametersIncrementer
                .start(step())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    // 5、作业增量参数--自定义时间戳
    @Bean
    public Job job6(){
        return new JobBuilder("Spring Batch Job DateIncr", jobRepository)
                .start(step())
                // 作业增量参数--自定义时间戳
                .incrementer(new DailyTimesParametersIncrementer())
                .build();
    }

    // 6、作业监听器 -- 接口实现方式
    @Bean
    public Job job7(){
        return new JobBuilder("Spring Batch Job Interface Listener", jobRepository)
                .start(step())
                // 作业监听器
                .listener(new CustomInterfaceJobExecutionListener())
                .build();
    }

    // 7、作业监听器 -- 注解实现方式
    @Bean
    public Job job8(){
        return new JobBuilder("Spring Batch Job Annotation Listener", jobRepository)
                .start(step())
                // 作业监听器：方式一
                // .listener(new CustomAnnoJobExecutionListener())
                // 作业监听器：方式二(两种方式都可以，只是写法不同)
                .listener(JobListenerFactoryBean.getListener(new CustomAnnoJobExecutionListener()))
                .build();
    }


    // TODO 获取作业及步骤上下文
    @Bean
    public Tasklet tasklet1(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("Spring Batch 执行 Job1");

                /**
                  TODO 通过获取(Job或者Step)上下文来获取同一个组件中的共享数据。
                    在一个Job中我可以通过获取 Job上下文 来共享或者修改 Job 或者 Step 里数据
                    而在同一个Job里的同一个Step中我可以通过获取 Step上下文 来共享或者修改 Step 里数据

                   TODO 注意：
                      不同的Step获取的是不同的Step上下文，所以无法在Step2中获取Step1里的数据。
                      在不同的Job里肯定是不能通过上下文获取共享数据的。但是，同一个Job里因为是包含多个步骤Step的，
                      所以只要获取到 Job上下文 就可以获取所有的Step中的共享数据。
                 */

                // 步骤Step
                // 获取Step上下文方式一：可以获取共享数据，但是不允许修改
                Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
                // 获取Step上下文方式二：通过执行上下文对象获取跟设置参数
                ExecutionContext stepExecutionContextEx = chunkContext.getStepContext().getStepExecution().getExecutionContext();
                stepExecutionContextEx.put("step1-tasklet1-key", "step1-tasklet1-value");

                System.out.println("=================1===============");

                // 作业Job
                // 获取Job上下文：通过执行上下文对象获取跟设置参数
                ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
                jobExecutionContext.put("job-step1-tasklet1-key", "job-step1-tasklet1-value");

                // 每个步骤都会包含一个完整的执行状态。这个状态通过RepeatStatus来表示
                return RepeatStatus.FINISHED;
            }
        };
    }

    // 批处理中的一部分任务
    @Bean
    public Tasklet tasklet2(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
                System.out.println("Spring Batch 执行 Job2");

                /*
                  TODO 通过获取(Job或者Step)上下文来获取同一个组件中的共享数据。
                    在一个Job中我可以通过获取 Job上下文 来共享或者修改 Job 或者 Step 里数据
                    而在同一个Job里的同一个Step中我可以通过获取 Step上下文 来共享或者修改 Step 里数据

                   TODO 注意：
                      不同的Step获取的是不同的Step上下文，所以无法在Step2中获取Step1里的数据。
                      在不同的Job里肯定是不能通过上下文获取共享数据的。但是，同一个Job里因为是包含多个步骤Step的，
                      所以只要获取到 Job上下文 就可以获取所有的Step中的共享数据。
                 */

                // 步骤Step
                // 获取Step上下文方式一：可以获取共享数据，但是不允许修改
                Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
                // 获取Step上下文方式二：通过执行上下文对象获取跟设置参数
                ExecutionContext stepExecutionContextEx = chunkContext.getStepContext().getStepExecution().getExecutionContext();
                System.err.println(stepExecutionContextEx.get("step1-tasklet1-key"));

                System.out.println("=================2===============");

                // 作业Job
                // 获取Job上下文：通过执行上下文对象获取跟设置参数
                ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
                System.err.println(jobExecutionContext.get("job-step1-tasklet1-key"));

                // 每个步骤都会包含一个完整的执行状态。这个状态通过RepeatStatus来表示
                return RepeatStatus.FINISHED;
            }
        };
    }

    // 批处理中的顺序步骤
    @Bean
    public Step step1(){
        return new StepBuilder("Spring Batch Step1", jobRepository)
                .tasklet(tasklet1(), batchTransactionManager)
                .build();
    }

    // 批处理中的顺序步骤
    @Bean
    public Step step2(){
        return new StepBuilder("Spring Batch Step2", jobRepository)
                .tasklet(tasklet2(), batchTransactionManager)
                .build();
    }


    @Bean
    public Job job9(){
        return new JobBuilder("Spring Batch Job Execution1", jobRepository)
                .start(step1())
                // 紧接step1()步骤后执行
                .next(step2())
                .build();
    }
}
