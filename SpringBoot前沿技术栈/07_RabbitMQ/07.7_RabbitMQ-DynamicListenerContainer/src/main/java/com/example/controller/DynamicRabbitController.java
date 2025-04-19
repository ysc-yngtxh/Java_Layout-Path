package com.example.controller;

import com.example.utils.RabbitUtil;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过封装好的工具类 RabbitUtil(RabbitAdmin) 实现动态创建、删除交换器、队列、绑定关系。
 * 不需要事先配置好交换器、队列、绑定关系等，可以在运行时动态创建、删除。
 */
@RestController
public class DynamicRabbitController {

    @Autowired
    private RabbitUtil rabbitUtil;

    /**
     * 创建交换器
     * @param exchangeType 交换器类型
     * @param exchangeName 交换器名称
     */
    @PostMapping("addExchange")
    public void addExchange(@RequestParam String exchangeType, @RequestParam String exchangeName) {
        rabbitUtil.addExchange(exchangeType, exchangeName);
    }

    /**
     * 删除交换器
     * @param exchangeName 交换器名称
     */
    @PostMapping("deleteExchange")
    public void deleteExchange(@RequestParam String exchangeName) {
        rabbitUtil.deleteExchange(exchangeName);
    }

    /**
     * 添加队列
     * @param queueName 队列名称
     */
    @PostMapping("addQueue")
    public void addQueue(@RequestParam String queueName) {
        rabbitUtil.addQueue(queueName);
    }

    /**
     * 删除队列
     * @param queueName 队列名称
     */
    @PostMapping("deleteQueue")
    public void deleteQueue(@RequestParam String queueName) {
        rabbitUtil.deleteQueue(queueName);
    }

    /**
     * 清空队列数据
     * @param queueName 队列名称
     */
    @PostMapping("purgeQueue")
    public void purgeQueue(@RequestParam String queueName) {
        rabbitUtil.purgeQueue(queueName);
    }

    /**
     * 添加绑定
     */
    @PostMapping("addBinding")
    public void addBinding(@RequestParam String exchangeType, @RequestParam String exchangeName,
                           @RequestParam String queueName, @RequestParam String routingKey) {
        rabbitUtil.addBinding(exchangeType, exchangeName, queueName, routingKey, false, null);
    }

    /**
     * 解除绑定
     */
    @PostMapping("removeBinding")
    public void removeBinding(@RequestParam String exchangeType, @RequestParam String exchangeName,
                              @RequestParam String queueName, @RequestParam String routingKey) {
        rabbitUtil.removeBinding(exchangeType, exchangeName, queueName, routingKey, false, null);
    }

    /**
     * 创建头部类型的交换器
     * 判断条件是所有的键值对都匹配成功才发送到队列
     */
    @PostMapping("andExchangeBindingQueueOfHeaderAll")
    public void andExchangeBindingQueueOfHeaderAll(@RequestParam String exchangeName
                                                 , @RequestParam String queueName) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue");
        header.put("bindType", "whereAll");
        rabbitUtil.andExchangeBindingQueue(RabbitUtil.ExchangeType.HEADERS, exchangeName, queueName, null, true, header);
    }

    /**
     * 创建头部类型的交换器
     * 判断条件是只要有一个键值对匹配成功就发送到队列
     */
    @PostMapping("andExchangeBindingQueueOfHeaderAny")
    public void andExchangeBindingQueueOfHeaderAny(@RequestParam String exchangeName
                                                 , @RequestParam String queueName) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue");
        header.put("bindType", "whereAny");
        rabbitUtil.andExchangeBindingQueue(RabbitUtil.ExchangeType.HEADERS, exchangeName, queueName, null, false, header);
    }
}
