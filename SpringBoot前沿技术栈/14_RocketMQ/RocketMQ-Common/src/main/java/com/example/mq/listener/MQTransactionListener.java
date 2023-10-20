package com.example.mq.listener;

import com.example.mq.annotation.MQHandlerActualizer;
import com.example.mq.handler.MQTransactionHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 事务消息监听器
 */
public class MQTransactionListener implements TransactionListener {

    @Autowired
    private Map<String, MQTransactionHandler> mqTransactionHandlerMap;

    /**
     * 用于执行本地事务的方法
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object obj) {
        MQTransactionHandler mqTransactionHandler = getListener(message.getTopic(), message.getTags());
        return mqTransactionHandler.executeLocalTransaction(message, obj);
    }

    /**
     * 用于回查本地事务执行结果的方法
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        MQTransactionHandler mqTransactionHandler = getListener(messageExt.getTopic(), messageExt.getTags());
        return mqTransactionHandler.checkLocalTransaction(messageExt);
    }

    private MQTransactionHandler getListener(String topic, String tags) {
        MQTransactionHandler mqTransactionHandler = null;
        for (Map.Entry<String, MQTransactionHandler> entry : mqTransactionHandlerMap.entrySet()) {
            MQHandlerActualizer msgHandlerActualizer = entry.getValue().getClass().getAnnotation(MQHandlerActualizer.class);
            if (msgHandlerActualizer != null) {
                String annotationTopic  = msgHandlerActualizer.topic();
                String[] annotationTags = msgHandlerActualizer.tags();
                if (!StringUtils.equals(topic, annotationTopic)) {
                    // 非该主题处理类
                    continue;
                }
                if(StringUtils.equals(annotationTags[0], "*")){
                    // 获取该实例
                    mqTransactionHandler = entry.getValue();
                    break;
                }
                boolean isContains = Arrays.asList(annotationTags).contains(tags);
                if(isContains){
                    // 注解类中包含tag则获取该实例
                    mqTransactionHandler = entry.getValue();
                    break;
                }
            }
        }
        return mqTransactionHandler;
    }
}
