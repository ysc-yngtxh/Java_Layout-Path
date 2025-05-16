package com.example.mq.config;

import com.example.mq.listener.MQTransactionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 12:30
 * @apiNote TODO 生产者 MQ 配置
 */
@Configuration
public class ProducerMQConfig {

	/***************************消息生产者***************************/
	@Autowired
	private MQTransactionListener mqTransactionListener;        // TODO 事务消息监听器

	// TODO 消息生产者配置信息
	@Value("${spring.application.name:application}")
	private String groupName;                                   // TODO 集群名称，这边以应用名称作为集群名称

	@Value("${rocketmq.producer.namesrvAddr:127.0.0.1:9876}")
	private String pNamesrvAddr;                                // TODO 生产者nameserver地址

	@Value("${rocketmq.producer.maxMessageSize:4096}")
	private Integer maxMessageSize;                            // TODO 消息最大大小，默认4M

	@Value("${rocketmq.producer.sendMsgTimeout:30000}")
	private Integer sendMsgTimeout;                             // TODO 消息发送超时时间，默认3秒

	@Value("${rocketmq.producer.retryTimesWhenSendFailed:2}")
	private Integer retryTimesWhenSendFailed;                   // TODO 消息发送失败重试次数，默认2次

	private static ExecutorService executor = new ThreadPoolExecutor(
			5,
			32,
			3,
			TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
			Executors.defaultThreadFactory(),
			new ThreadPoolExecutor.AbortPolicy());      // TODO 执行任务的线程池

	// 普通消息生产者
	@Bean("defaultProvider")
	public DefaultMQProducer getDefaultMQProducer() {
		DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
		producer.setNamesrvAddr(this.pNamesrvAddr);
		producer.setMaxMessageSize(this.maxMessageSize);
		producer.setSendMsgTimeout(this.sendMsgTimeout);
		producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
		try {
			producer.start();
		} catch (MQClientException e) {
			System.out.println(e.getErrorMessage());
		}
		return producer;
	}

	// 事务消息生产者（rocketmq支持柔性事务）
	@Bean("transaction")
	public TransactionMQProducer getTransactionMQProducer() {
		// 初始化事务消息基本与普通消息生产者一致
		TransactionMQProducer producer = new TransactionMQProducer("transaction_" + this.groupName);
		producer.setNamesrvAddr(this.pNamesrvAddr);
		producer.setMaxMessageSize(this.maxMessageSize);
		producer.setSendMsgTimeout(this.sendMsgTimeout);
		producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);

		// 添加事务消息处理线程池
		producer.setExecutorService(executor);
		// 添加事务消息监听
		producer.setTransactionListener(mqTransactionListener);
		try {
			producer.start();
		} catch (MQClientException e) {
			System.out.println(e.getErrorMessage());
		}
		return producer;
	}
}
