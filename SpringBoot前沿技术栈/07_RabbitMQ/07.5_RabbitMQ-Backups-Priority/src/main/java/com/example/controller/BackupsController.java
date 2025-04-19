package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BackupsController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 开始发消息  测试确认
    @GetMapping("/backups/{message}")
    public void sendBackups(@PathVariable String message) {
        CorrelationData correlationData1 = new CorrelationData("1");
        rabbitTemplate.convertAndSend("backupsExchange", "backupsRoutingKey", message, correlationData1);
        log.info("发送消息内容：{}，设置正常的绑定交换机与路由backupsRoutingKey", message);

        // 当我们的路由出现问题的时候，正常来说：消息路由不到队列无法被处理就会调用 returnedMessage()方法被退回。
        // 但是由于我们声明的「backupsExchange」交换机的时候关联了备份交换机「fanoutExchange」
        // 就会导致其路由不到队列的消息进入备份交换机，而备份交换机又是广播类型的交换机，因此备份交换机下的所有队列都会得到这条消息
        // 因此，我备份交换机和其下的所有队列都得到消息了，就不用退回了，也就没有执行 returnedMessage()方法
        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend("backupsExchange", "backupsRoutingKey1", message, correlationData2);
        log.info("发送消息内容：{}，设置不正常的绑定交换机与路由backupsRoutingKey1", message);

        // TODO 如果想让程序执行 returnedMessage()方法，先删除服务器里的 backupsExchange交换机(因为绑定过备份交换机，避免又走备份)
        //  然后删除 @Bean("backupsExchange")方法里的备份交换机配置。重新执行，即可实现退回消息
    }
}
