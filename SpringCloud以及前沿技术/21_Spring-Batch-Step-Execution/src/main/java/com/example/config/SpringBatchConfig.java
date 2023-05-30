package com.example.config;

import com.example.listener.CustomStepListener;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-25 23:51
 * @apiNote TODO 批处理配置类
 */
@Configuration
public class SpringBatchConfig {

    @Resource
    private JobRepository jobRepository;  // SpringBatch数据库的操作

    @Resource
    private PlatformTransactionManager batchTransactionManager;  // 事务管理器

    private static int timer = 10;

    /** TODO
      *  ItemReader会一直循环读，直到返回null才停止；
      *  而processor也是一样，ItemReader读多少次，他就处理多少次；
      *  ItemWriter一次性输出当前次输入的所有数据
     */
    // 读操作
    @Bean
    public ItemReader<String> itemReader(){
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
    public ItemProcessor<String, String> itemProcessor(){
        return new ItemProcessor<String, String>() {
            @Override
            public String process(String item) throws Exception {
                System.out.println("===========process==========" +item);
                return "process-ret->" + item;
            }
        };
    }

    // 处理操作
    @Bean
    public ItemWriter<String> itemWriter(){
        return new ItemWriter<String>() {
            @Override
            public void write(Chunk<? extends String> chunk) throws Exception {
                System.out.println(chunk);
            }
        };
    }

    @Bean
    public Step step(){
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
    public Job job1(){
        return new JobBuilder("Job", jobRepository)
                .start(step())
                .build();
    }
}
