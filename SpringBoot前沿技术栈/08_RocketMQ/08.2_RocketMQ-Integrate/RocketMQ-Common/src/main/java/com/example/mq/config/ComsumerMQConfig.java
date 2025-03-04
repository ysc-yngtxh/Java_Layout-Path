package com.example.mq.config;

import com.example.mq.handler.MQHandler;
import com.example.mq.listener.MQConcurrentListener;
import java.util.Map;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 12:32
 * @apiNote TODO 消费者 MQ 配置
 */
@Configuration
public class ComsumerMQConfig {

    /***************************消息消费者***************************/
    @Autowired
    private Map<String, MQHandler> mqHandlerMap;

    // TODO 消息消费者配置信息
    @Value("${spring.application.name:application}")
    private String groupName;                    // TODO 集群名称，这边以应用名称作为集群名称

    @Value("${rocketmq.consumer.namesrvAddr:127.0.0.1:9876}")
    private String cNamesrvAddr;                 // TODO 消费者nameserver地址

    @Value("${rocketmq.consumer.consumeThreadMin:20}")
    private int consumeThreadMin;                // TODO 最小线程数

    @Value("${rocketmq.consumer.consumeThreadMax:64}")
    private int consumeThreadMax;                // TODO 最大线程数

    @Value("${rocketmq.consumer.topics:message~*}")
    private String topics;                       // TODO 消费者监听主题，多个主题以分号隔开（topic~tag;topic~tag）

    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize:1}")
    private int consumeMessageBatchMaxSize;      // TODO 一次消费消息的条数，默认为1条

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(cNamesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(getMessageListenerConcurrently());
        // TODO 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费,如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // TODO 设置消费模型，集群还是广播，默认为集群
        //listener.setMessageModel(MessageModel.CLUSTERING);
        // TODO 设置一次消费消息的条数，默认为1条
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            // TODO 设置该消费者订阅的主题和tag，如果是订阅该主题下的所有tag，则tag使用*；如果需要指定订阅该主题下的某些tag，则使用||分割，例如tag1||tag2||tag3
            String[] topicTagsArr = topics.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0], topicTag[1]);
            }
            consumer.start();
        } catch (Exception e){
            throw new Exception(e);
        }
        return consumer;
    }

    // TODO 并发消息侦听器(如果对顺序消费有需求则使用 MessageListenerOrderly 有序消息侦听器)
    @Bean
    public MessageListenerConcurrently getMessageListenerConcurrently() {
        return new MQConcurrentListener(mqHandlerMap);
    }
}
