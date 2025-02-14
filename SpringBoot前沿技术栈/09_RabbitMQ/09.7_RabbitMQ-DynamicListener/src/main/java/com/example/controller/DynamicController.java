package com.example.controller;

import com.alibaba.fastjson2.JSON;
import com.example.pojo.ConsumerInfo;
import com.example.utils.RabbitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DynamicController {

    @Autowired
    private SimpleMessageListenerContainer container;

    @Autowired
    private RabbitUtil rabbitUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 添加队列到监听器
     */
    @PostMapping("/addQueueToListener")
    public void addQueue(@RequestBody ConsumerInfo consumerInfo) {
        boolean existQueue = rabbitUtil.existQueue(consumerInfo.getQueueName());
        if(!existQueue) {
            log.error("当前 {} 队列不存在", consumerInfo.getQueueName());
        }
        // 消费mq消息的类
        container.addQueueNames(consumerInfo.getQueueName());
        // 打印监听容器中正在监听到队列
        log.info("container-queue:{}", JSON.toJSON(container.getQueueNames()));
    }

    /**
     * 移除正在监听的队列
     */
    @PostMapping("removeQueueFromListener")
    public void removeQueue(@RequestBody ConsumerInfo consumerInfo) {
        // 消费mq消息的类
        container.removeQueueNames(consumerInfo.getQueueName());
        // 打印监听容器中正在监听到队列
        log.info("container-queue:{}", JSON.toJSON(container.getQueueNames()));
    }

    /**
     * 查询监听容器中正在监听到队列
     */
    @PostMapping("queryListenerQueue")
    public void queryListenerQueue() {
        log.info("container-queue:{}", JSON.toJSON(container.getQueueNames()));
    }

    /**
     * 向监听容器中存在的队列发送消息
     */
    @PostMapping("/sendMsg")
    public void sendMsg(@RequestParam String queueName, @RequestParam String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}