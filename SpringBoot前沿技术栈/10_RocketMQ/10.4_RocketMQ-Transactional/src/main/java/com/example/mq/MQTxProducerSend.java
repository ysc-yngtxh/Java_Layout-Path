package com.example.mq;

import com.alibaba.fastjson.JSON;
import com.example.pojo.UserCharge;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 21:44
 * @apiNote TODO
 */
@Slf4j
@Component
public class MQTxProducerSend {

    private static final String Topic = "RLT_TEST_TOPIC";
    private static final String Tag = "charge";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 先向MQ Server发送半消息
     * @param userCharge 用户充值信息
     */
    public TransactionSendResult sendHalfMsg(UserCharge userCharge) {
        // 生成生产事务id
        String transactionId = UUID.randomUUID().toString().replace("-", "");
        log.info("【发送半消息】transactionId={}", transactionId);

        // 发送事务消息（参1：生产者所在事务组，参2：topic+tag，参3：消息体(可以传参)，参4：发送参数）
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
                 Topic + ":" + Tag,
                MessageBuilder.withPayload(userCharge).setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId).build(),
                userCharge);
        log.info("【发送半消息】sendResult={}", JSON.toJSONString(sendResult));
        return sendResult;
    }
}

