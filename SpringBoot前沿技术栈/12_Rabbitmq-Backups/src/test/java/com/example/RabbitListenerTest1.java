package com.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 这个是test中相关联的例子，随便理解,跟这个整体项目不相关
 */
@Component
public class RabbitListenerTest1 {

    // 监听消息中间件的队列work。第二个参数是排外，如果排外了则这个队列只允许一个消费者监听。第三个参数是持久化
    // AMQP 默认消息是持久化的，但只有在队列也是持久化时才有作用
    @RabbitListener(queuesToDeclare = @Queue(value = "work", exclusive = "false", durable = "false"))
    public void receivel1(String message){
        System.out.println("message1 = " + message);
    }

    // 监听消息中间件的队列hello。第二个参数是排外，如果排外了则这个队列只允许一个消费者监听。第三个参数是持久化
    // AMQP 默认消息是持久化的，但只有在队列也是持久化时才有作用
    @RabbitListener(queuesToDeclare = @Queue(value = "hello", exclusive = "false", durable = "false"))
    public void receivel2(String message){
        System.out.println("message2 = " + message);
    }

    // 监听消息中间件的队列hello。第二个参数是排外，如果排外了则这个队列只允许一个消费者监听。第三个参数是持久化
    // AMQP 默认消息是持久化的，但只有在队列也是持久化时才有作用
    @RabbitListener(queuesToDeclare = @Queue(value = "hello", exclusive = "false", durable = "false"))
    public void receivel3(String message){
        System.out.println("message3 = " + message);
    }

    @RabbitListener(bindings = {  // bindings是交换机连接队列的规则
            @QueueBinding(
                    // 交换机的名称和类型和持久化
                    exchange = @Exchange(value = "logs", type= "fanout", durable="false"),
                    value = @Queue // 这里没有给Queue的value值，表示是个暂时的队列
            )
    })
    public void receivel4(String message){
        System.out.println("message4 = " + message);
    }

    @RabbitListener(bindings = {  // bindings是交换机连接队列的规则
            @QueueBinding(
                    // 交换机的名称和类型
                    exchange = @Exchange(value = "info", type= "direct"),
                    key = {"error", "work"}, // 交换机的key
                    value = @Queue(exclusive = "false")  // 这里没有给Queue的value值，表示是个暂时的队列

            )
    })
    public void receivel5(String message){
        System.out.println("message5 = " + message);
    }

    @RabbitListener(bindings = {  // bindings是交换机连接队列的规则
            @QueueBinding(
                    exchange = @Exchange(value = "ect", type= "topic"), // 交换机的名称和类型
                    key = {"save.*"},  // 交换机的key
                    value = @Queue()   // 这里没有给Queue的value值，表示是个暂时的队列
            )
    })
    public void receivel6(String message){
        System.out.println("message6 = "+message);
    }


    // 使用@RabbitListener监听，不管有木有消费掉，反正队列中是没得了😭。
    // 所以如果你不想被无端消费掉，就只注册RabbitMQ不配置监听，等到需要消费的时候去进行手动消费
    @Bean("yscExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange("ysc").durable(true).build();
    }
    @Bean("yscQueue")
    public org.springframework.amqp.core.Queue directQueue(){
        return QueueBuilder.durable("ysc").build();
    }
    @Bean
    public Binding directBinding(@Qualifier("yscExchange") DirectExchange yscExchange,
                                 @Qualifier("yscQueue") org.springframework.amqp.core.Queue yscQueue){
        return BindingBuilder.bind(yscQueue).to(yscExchange).with("");
    }

    @RabbitListener(bindings = {  // bindings是交换机连接队列的规则
            @QueueBinding(
                    exchange = @Exchange(value = "cjq", type= "topic"), // 交换机的名称和类型
                    value = @Queue() // 这里没有给Queue的value值，表示是个暂时的队列
            )
    })
    public void confirm(String message){
        System.out.println("message1 = " + message);
    }
    @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }
}
