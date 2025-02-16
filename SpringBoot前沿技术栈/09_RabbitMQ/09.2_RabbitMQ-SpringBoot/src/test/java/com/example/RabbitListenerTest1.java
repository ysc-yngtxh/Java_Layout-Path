package com.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-04 08:22
 * @apiNote TODO
 */
@Component
public class RabbitListenerTest1 {

    // 监听消息中间件的队列rabbitQueue1。第二个参数是排外，如果排外了则这个队列只允许一个消费者监听。第三个参数是持久化
    // AMQP 默认消息是持久化的，但只有在队列也是持久化时才有作用
    @RabbitListener(queuesToDeclare = @Queue(value = "rabbitQueue1", exclusive = "false", durable = "true"))
    public void receive1(String message){
        System.out.println("message = " + message);
    }


    // 监听消息中间件的队列rabbitQueue2。第二个参数是排外，如果排外了则这个队列只允许一个消费者监听。第三个参数是持久化
    // AMQP 默认消息是持久化的，但只有在队列也是持久化时才有作用
    @RabbitListener(queuesToDeclare = @Queue(value = "rabbitQueue2", exclusive = "false", durable = "true"))
    public void receive2(String message){
        System.out.println("message1 = " + message);
    }

    // 监听消息中间件的队列rabbitQueue2 。第二个参数是排外，如果排外了则这个队列只允许一个消费者监听。第三个参数是持久化
    // AMQP 默认消息是持久化的，但只有在队列也是持久化时才有作用
    @RabbitListener(queuesToDeclare = @Queue(value = "rabbitQueue2", exclusive = "false", durable = "true"))
    public void receive3(String message){
        System.out.println("message2 = " + message);
    }


    @RabbitListener(bindings = {  // bindings是交换机连接队列的规则
            @QueueBinding(
                    // 交换机的名称和类型和持久化
                    exchange = @Exchange(value = "rabbitExchange1", type= "fanout", durable="false"),
                    value = @Queue // 这里没有给Queue的value值，表示是个暂时的队列
            )
    })
    public void receive4(String message){
        System.out.println("message = " + message);
    }


    @RabbitListener(bindings = {  // bindings是交换机连接队列的规则
            @QueueBinding(
                    // 交换机的名称和类型
                    exchange = @Exchange(value = "rabbitExchange2", type= "direct"),
                    key = {"error", "normal"}, // 交换机的key
                    value = @Queue(exclusive = "false")  // 这里没有给Queue的value值，表示是个暂时的队列
            )
    })
    public void receive5(String message){
        System.out.println("message = " + message);
    }


    @RabbitListener(bindings = {  // bindings是交换机连接队列的规则
            @QueueBinding(
                    exchange = @Exchange(value = "rabbitExchange3", type= "topic"), // 交换机的名称和类型
                    key = {"save.*"},  // 交换机的key
                    value = @Queue()   // 这里没有给Queue的value值，表示是个暂时的队列
            )
    })
    public void receive6(String message){
        System.out.println("message = "+message);
    }


    // 使用 @RabbitListener 监听，不管有木有消费掉，反正队列中是没得了😭。
    // 所以如果你不想被无端消费掉，就只注册RabbitMQ不配置监听，等到需要消费的时候去进行手动消费
    @Bean("rabbitExchange4")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange("rabbitExchange4").durable(true).build();
    }
    @Bean("rabbitQueue4")
    public org.springframework.amqp.core.Queue directQueue(){
        return QueueBuilder.durable("rabbitQueue4").build();
    }
    @Bean
    public Binding directBinding(@Qualifier("rabbitExchange4") DirectExchange rabbitExchange4,
                                 @Qualifier("rabbitQueue4") org.springframework.amqp.core.Queue rabbitQueue4){
        return BindingBuilder.bind(rabbitQueue4).to(rabbitExchange4).with("");
    }
}
