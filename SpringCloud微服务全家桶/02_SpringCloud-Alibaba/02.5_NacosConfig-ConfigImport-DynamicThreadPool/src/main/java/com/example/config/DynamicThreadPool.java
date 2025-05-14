package com.example.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.shaded.com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-26 22:30
 * @apiNote TODO SpringBoot + Nacos 实现了一个动态化线程池
 */
// 这个@RefreshScope 是Spring Cloud中的一个注解，用来实现Bean中属性的动态刷新。
// 使用 @RefreshScope 注解的会生成一个代理对象，当属性发生变更的时候，代理对象会将原先的属性Bean清除，
// 然后重新创建Bean，代理对象会从重新创建的Bean中获取属性数据。
@RefreshScope
@Configuration
public class DynamicThreadPool implements InitializingBean {
 
    @Value("${nacos.core.size}")
    private String coreSize;

    // 如果同时在 Nacos 和本地配置文件（如 bootstrap.properties、application.properties）中存在相同属性名的配置，
    // 那么 @NacosValue 注解获取的是 Nacos 中的属性值，而不是本地配置文件。
    @Value(value = "${nacos.max.size}")
    private String maxSize;
 
    private static ThreadPoolExecutor threadPoolExecutor;

    // Nacos的配置管理器对象
    @Autowired
    private NacosConfigManager nacosConfigManager;

    // Nacos的配置文件对象
    @Autowired
    private NacosConfigProperties nacosConfigProperties;
 
    @Override
    public void afterPropertiesSet() throws Exception {
        // 按照nacos配置初始化线程池
        threadPoolExecutor = new ThreadPoolExecutor(
                Integer.parseInt(coreSize),
                Integer.parseInt(maxSize),
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("c_t_%d").build(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("rejected!");
                    }
                });
 
        // 监听Nacos配置变更
        nacosConfigManager.getConfigService().addListener("shared_config.yml", nacosConfigProperties.getGroup(),
                new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }
 
                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        // 配置变更，修改线程池配置
                        System.out.println(configInfo);
                        changeThreadPoolConfig(Integer.parseInt(coreSize), Integer.parseInt(maxSize));
                    }
                });
    }

    /**
     * 修改线程池核心参数
     *
     * @param coreSize
     * @param maxSize
     */
    private void changeThreadPoolConfig(int coreSize, int maxSize) {
        threadPoolExecutor.setCorePoolSize(coreSize);
        threadPoolExecutor.setMaximumPoolSize(maxSize);
    }
 
    /**
     * 打印当前线程池的状态
     */
    public String printThreadPoolStatus() {
        return String.format("core_size:%s, thread_current_size:%s; " +
                        "thread_max_size:%s; queue_current_size:%s, total_task_count:%s",
                threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getMaximumPoolSize(), threadPoolExecutor.getQueue().size(),
                threadPoolExecutor.getTaskCount());
    }
 
    /**
     * 给线程池增加任务
     *
     * @param count
     */
    public void dynamicThreadPoolAddTask(int count) {
        for (int i = 0; i < count; i++) {
            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(finalI);
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
