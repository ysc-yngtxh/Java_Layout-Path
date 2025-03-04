package com.example.callback;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 回调接口
 */
@Slf4j
@Component
public class RabbitMQCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    // RabbitMQCallBack 实现的是内部接口 ConfirmCallback 是交换机消息确认。ReturnCallback 是实现消息回退模式
    // 通过实现 ConfirmCallback 接口，消息发送到 Exchange 后触发回调，确认是否正确到达 Exchange 中
    // 通过实现 ReturnCallback 接口，如果消息不可路由，通过其实现方法来让消息回退给生产者

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 使用此方式就是将 ConfirmCallback、ReturnsCallback 。注入到 RabbitTemplate 当中。
    // 这样在启动时候才会去执行 confirm 方法确认消息发布 。或者执行 returnedMessage 方法进行消息回退。
    @PostConstruct  // 被注解的方法将在bean创建并且注入完成，在执行初始化方法之前调用。原理：后处理器
    public void init() {
        rabbitTemplate.setConfirmCallback(this);  // 指定 ConfirmCallback
        rabbitTemplate.setReturnsCallback(this);  // 指定 ReturnsCallback
    }

    /**
     * 交换机确认回调方法
     * 1、发消息 交换机接收到了  回调
     *    1.1、 correlationData  保存回调消息的ID及相关信息
     *    1.2、 交换机收到消息 ack = true
     *    1.3、 cause 失败的原因
     * 2、当生产者将信道设置为Confirm模式时，所有在该信道上发布的消息都会被分配一个唯一的ID。
     *    当消息被投递到所有匹配的队列后，RabbitMQ的Broker会发送一个包含消息唯一ID的确认信息给生产者。
     *    这确保了生产者知道消息已经正确地到达了目的队列。
     */
    // 注意：要想发布确认，不光要实现confirm方法，还需要在配置文件中spring.rabbitmq.publisher-confirm-type=correlated
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // CorrelationData correlationData 这个是发送端convertAndSend自己发送的东西
        String id = correlationData != null ? correlationData.getId() : "";
        // ack是一个布尔值，表示消息是否被成功被交换机接收到了？
        if (ack) {
            log.info("交换机在确认消息的回调confirm()方法中收到Id为：{}的消息", id);
        } else {
            log.info("交换机在确认消息的回调confirm()方法中还未收到Id为：{}的消息，由于原因:{}", id, cause);
        }
    }

    /**
     * 当消息传递过程中不可达目的地【队列】时将消息返回给生产者
     * 只有不可达目的地【队列】的时候，才进行回退
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("在回调returnedMessage()方法中，消息:{} 被交换机{}退回，回应码：{}，退回原因：{}，路由{}"
                , new String(returned.getMessage().getBody())
                , returned.getExchange()
                , returned.getReplyCode()
                , returned.getReplyText()
                , returned.getRoutingKey()
        );
    }
}
