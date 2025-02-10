package com.example.controller;

import com.example.mq.MQTxProducerSend;
import com.example.pojo.UserCharge;
import com.example.vo.Result;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 21:41
 * @apiNote TODO
 */
@RestController
public class MQTxController {

    @Autowired
    private MQTxProducerSend mqTxProducerSend;

    @PostMapping("/charge")
    public Result<TransactionSendResult> charge(@RequestBody UserCharge userCharge) {
        TransactionSendResult sendResult = mqTxProducerSend.sendHalfMsg(userCharge);
        return Result.success(sendResult);
    }
}
