package com.example.config;

import com.example.dto.User;
import com.example.processor.CustomizeItemProcessor;
import com.example.service.Impl.UserServiceImpl;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.ScriptItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.script.ScriptEngineManager;
import java.util.Arrays;

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


    // 读取文件 user-validation.txt 数据
    // TODO ValidatingItemProcessor: 校验处理器 (校验name值不为null和空值)
    //  BeanValidatingItemProcessor 是 ValidatingItemProcessor 子类
    //  核心 BeanValidatingItemProcessor 类是Spring Batch提供现成的Validator校验类，直接使用即可
    @Bean
    public FlatFileItemReader<User> itemReader1(){
        return new FlatFileItemReaderBuilder<User>()
                .name("File Convert user-validation ItemReader")
                // 获取文件
                .resource(new ClassPathResource("user-validation.txt"))
                // 解析数据 -- 指定解析其使用#分割 -- 默认是，逗号
                .delimited().delimiter("#")
                // 按照#截取数据之后，数据怎么命名
                .names("Id", "name", "age")
                // 封装数据 -- 将读取的数据封装到对象：User对象
                .targetType(User.class) // 自动封装
                .build();
    }
    // 校验参数是否合法，不合法则丢弃，并不会产生异常
    @Bean
    public BeanValidatingItemProcessor<User> beanValidatingItemProcessor(){
        BeanValidatingItemProcessor<User> itemProcessor = new BeanValidatingItemProcessor<>();
        itemProcessor.setFilter(true);
        return itemProcessor;
    }
    @Bean
    public ItemWriter<User> itemWriter1() {
        return new ItemWriter<User>() {
            @Override
            public void write(@NonNull Chunk<? extends User> chunk) throws Exception {
                chunk.getItems().forEach(System.out::println);
            }
        };
    }
    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                // 这里多了一步操作，以便引入ItemProcessor组件springboot
                .processor(beanValidatingItemProcessor())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job1() {
        return new JobBuilder("Validating Processor Job1", jobRepository)
                .start(step1())
                .build();
    }


    // 读取文件 user-adapter.txt 数据
    // TODO ItemProcessorAdapter: 适配器处理器(适配逻辑将name转为大写)
    //  项目业务中已经有的校验规则，我们直接通过适配器引用就行，不需要重复代码逻辑
    @Bean
    public FlatFileItemReader<User> itemReader2(){
        return new FlatFileItemReaderBuilder<User>()
                .name("File Convert user-adapter ItemReader")
                // 获取文件
                .resource(new ClassPathResource("user-adapter.txt"))
                // 解析数据 -- 指定解析其使用#分割 -- 默认是，逗号
                .delimited().delimiter("#")
                // 按照#截取数据之后，数据怎么命名
                .names("Id", "name", "age")
                // 封装数据 -- 将读取的数据封装到对象：User对象
                .targetType(User.class) // 自动封装
                .build();
    }
    @Bean
    public ItemProcessorAdapter<User, User> itemProcessorAdapter(){
        ItemProcessorAdapter<User, User> adapter = new ItemProcessorAdapter<>();
        // 适配目标方法
        adapter.setTargetMethod("toUpperCase");
        // 适配目标对象
        adapter.setTargetObject(new UserServiceImpl());
        return adapter;
    }
    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader2())
                // 引入适配器处理器
                .processor(itemProcessorAdapter())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    @Primary
    public Job job2() {
        return new JobBuilder("Adapter Processor Job", jobRepository)
                .start(step2())
                .build();
    }


    // TODO ScriptItemProcessor: 脚本处理器(将name转为大写)
    @Bean
    public ScriptItemProcessor<User, User> scriptItemProcessor(){
        ScriptItemProcessor<User, User> scriptItemProcessor = new ScriptItemProcessor<>();
        // 加载js文件，执行js中转换逻辑
        scriptItemProcessor.setScript(new ClassPathResource("userScript.js"));
        return scriptItemProcessor;
    }
    // TODO 这里需要注意的是，在jdk在11开始就标注要取消NashornScriptEngineFactory类，在17中删除。给出的理由是因为jdk中维护不方便。
    //  所以我们可以在maven中加入对应的开发包。这里使用了 Nashorn 引擎，并通过 registerEngineExtension 方法将其与 .js 文件扩展名关联起来。
    //  JDK17之前的版本自带js匹配引擎，不受影响
    @Bean
    public ScriptEngineManager scriptEngineManager() {
        ScriptEngineManager factory = new ScriptEngineManager();
        factory.registerEngineExtension("js", new NashornScriptEngineFactory());
        return factory;
    }
    @Bean
    public Step step3() {
        return new StepBuilder("step3", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader2())
                // 引入脚本处理器
                .processor(scriptItemProcessor())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job3() {
        return new JobBuilder("Script Processor Job", jobRepository)
                .start(step3())
                .build();
    }

    // TODO CompositeItemProcessor：组合处理器
    //  将 校验处理器 和 脚本处理器 组合。先校验参数是否合法，然后再通过脚本将name值转为大写
    @Bean
    public CompositeItemProcessor<User, User> compositeItemProcessor(){
        CompositeItemProcessor<User, User> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(Arrays.asList(beanValidatingItemProcessor(), scriptItemProcessor()));
        return compositeItemProcessor;
    }
    @Bean
    public Step step4() {
        return new StepBuilder("step4", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader1())
                // 引入组合处理器
                .processor(compositeItemProcessor())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job4() {
        return new JobBuilder("Composite Processor Job", jobRepository)
                .start(step4())
                .build();
    }

    // TODO 自定义处理器
    @Bean
    public Step step5() {
        return new StepBuilder("step5", jobRepository)
                .<User, User>chunk(1, batchTransactionManager)
                .reader(itemReader2())
                // 引入自定义处理器
                .processor(new CustomizeItemProcessor())
                .writer(itemWriter1())
                .build();
    }
    @Bean
    public Job job5() {
        return new JobBuilder("Customize Processor Job", jobRepository)
                .start(step5())
                .build();
    }
}
