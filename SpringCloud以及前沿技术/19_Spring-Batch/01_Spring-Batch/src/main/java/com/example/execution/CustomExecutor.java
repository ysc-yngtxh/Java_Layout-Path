package com.example.execution;

import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-26 19:03
 * @apiNote TODO 执行批处理操作
 */
@Component
@Lazy(value = false)
public class CustomExecutor implements ApplicationContextAware {

    /**
     * Aware接口及其子接口
     * 会在refreshContext()方法中实例化所有bean实例(可以获取到所有的bean实例对象)，调用Aware子接口的接口方法。
     * 用来获取Spring启动时相关的对象，在项目启动后需要用到时直接调用
     */
    @Override
    @SneakyThrows
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // JobLauncher --- 作业启动器
        JobLauncher jobLauncher = (JobLauncher)applicationContext.getBean("jobLauncher");
        Job job = (Job)applicationContext.getBean("job");
        // 这里传递的是批处理参数,相同参数的批处理只能执行一次，否则会报异常,且这时的参数为空
        jobLauncher.run(job, new JobParameters());
    }
}
