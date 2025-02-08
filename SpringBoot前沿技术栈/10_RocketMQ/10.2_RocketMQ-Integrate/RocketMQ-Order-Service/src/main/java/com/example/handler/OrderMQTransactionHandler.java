package com.example.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RocketOrder;
import com.example.mq.annotation.MQHandlerActualizer;
import com.example.mq.handler.MQTransactionHandler;
import com.example.service.RocketOrderService;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO rocketmq-example
 */
@MQHandlerActualizer(topic = "points")
public class OrderMQTransactionHandler implements MQTransactionHandler {

    @Autowired
    private RocketOrderService orderService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        // 执行本地事务
        LocalTransactionState state;
        try {
            String body = new String(message.getBody());
            RocketOrder order = JSONObject.parseObject(body, RocketOrder.class);
            // 执行本地事务
            orderService.save(order);
            state = LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e){
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        // 回查本地事务是否已提交
        LocalTransactionState state;
        String body = new String(messageExt.getBody());
        RocketOrder tempOrder = JSONObject.parseObject(body, RocketOrder.class);
        // 如果数据存在，则事务提交成功
        RocketOrder order = orderService.getById(tempOrder.getOrderId());
        if (order != null) {
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }
}