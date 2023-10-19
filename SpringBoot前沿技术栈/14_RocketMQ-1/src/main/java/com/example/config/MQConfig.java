package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:54
 * @apiNote TODO
 */
@Configuration
public class MQConfig {

    @Value("${spring.application.name:application}")
    private String groupName;                    // TODO 集群名称，这边以应用名称作为集群名称

    /***************************消息生产者***************************/

    // TODO 消息消费者配置信息
    @Value("${rocketmq.consumer.namesrvAddr:127.0.0.1:9876}")
    private String namesrvAddr;                 // TODO 消费者nameserver地址

    @Value("${rocketmq.consumer.consumeThreadMin:20}")
    private int consumeThreadMin;                // TODO 最小线程数

    @Value("${rocketmq.consumer.consumeThreadMax:64}")
    private int consumeThreadMax;                // TODO 最大线程数

    @Value("${rocketmq.consumer.topics:test~*}")
    private String topics;                       // TODO 消费者监听主题，多个主题以分号隔开（topic~tag;topic~tag）

    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize:1}")
    private int consumeMessageBatchMaxSize;      // TODO 一次消费消息的条数，默认为1条

    @Value("${rocketmq.producer.maxMessageSize:4096}")
    private Integer maxMessageSize ;             // TODO 消息最大大小，默认4M

    @Value("${rocketmq.producer.sendMsgTimeout:30000}")
    private Integer sendMsgTimeout;              // TODO 消息发送超时时间，默认3秒

    @Value("${rocketmq.producer.retryTimesWhenSendFailed:2}")
    private Integer retryTimesWhenSendFailed;    // TODO 消息发送失败重试次数，默认2次
}
