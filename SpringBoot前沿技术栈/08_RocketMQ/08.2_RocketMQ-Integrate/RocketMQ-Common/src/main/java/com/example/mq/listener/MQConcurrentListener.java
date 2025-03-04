package com.example.mq.listener;

import com.example.mq.annotation.MQHandlerActualizer;
import com.example.mq.handler.MQHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 并发消息监听器
 */
public class MQConcurrentListener implements MessageListenerConcurrently {

    @Autowired
    private Map<String, MQHandler> mqHandlerMap;

    public MQConcurrentListener(Map<String, MQHandler> mqHandlerMap) {
        this.mqHandlerMap = mqHandlerMap;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if(CollectionUtils.isEmpty(list)){
            System.out.println("接受到的消息为空，不处理，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.getFirst();
        // TODO 判断该消息是否重复消费（RocketMQ不保证消息不重复，如果你的业务需要保证严格的不重复消息，需要你自己在业务端去重）
        // TODO 获取该消息重试次数
        int reconsume = messageExt.getReconsumeTimes();
        if(reconsume == 3) { // 消息已经重试了3次，需做告警处理，以及相关日志
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        // TODO 处理对应的业务逻辑
        String topic = messageExt.getTopic();
        String tags  = messageExt.getTags();
        System.out.println("接受到的消息主题为：" + topic + "; tag为：" + tags);
        MQHandler mqMsgHandler = null;
        // 获取消息处理类中的topic和tag注解，根据topic和tag进行策略分发出来具体业务
        for (Map.Entry<String, MQHandler> entry : mqHandlerMap.entrySet()) {
            MQHandlerActualizer msgHandlerActualizer = entry.getValue().getClass().getAnnotation(MQHandlerActualizer.class);
            if (msgHandlerActualizer == null) {
                // 非消息处理类
                continue;
            }
            String annotationTopic = msgHandlerActualizer.topic();
            if (!StringUtils.equals(topic, annotationTopic)) {
                // 非该主题处理类
                continue;
            }
            String[] annotationTags = msgHandlerActualizer.tags();
            if(StringUtils.equals(annotationTags[0],"*")){
                // 获取该实例
                mqMsgHandler = entry.getValue();
                break;
            }
            boolean isContains = Arrays.asList(annotationTags).contains(tags);
            if(isContains){
                // 注解类中包含tag则获取该实例
                mqMsgHandler = entry.getValue();
                break;
            }
        }
        if (mqMsgHandler == null) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        ConsumeConcurrentlyStatus status = mqMsgHandler.handle(tags, messageExt);
        // 如果没有return success,consumer会重新消费该消息，直到return success
        return status;
    }
}
