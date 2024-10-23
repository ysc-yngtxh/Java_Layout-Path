package com.example;

import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitBackupsApplication.class)
class RabbitBackupsApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 1、生产者-队列-消费者
    @Test
    public void textWork() {
        rabbitTemplate.convertAndSend("work", "第一种,基本模型");
    }

    // 2、生产者-队列-多个消费者(默认在spring AMQP实现公平调度)
    @Test
    public void textHello() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("hello", "第二种,能者多劳模型");
        }
    }

    // 3、生产者-交换机(广播)-队列-消费者
    @Test
    public void textFanout(){
        rabbitTemplate.convertAndSend("logs", "", "第三种,广播Fanout模型");
    }

    // 4、生产者-交换机(direct)-路由-队列-消费者
    @Test
    public void textDirect(){
        rabbitTemplate.convertAndSend("info", "error", "第四种,路由Direct模型(error)");
        rabbitTemplate.convertAndSend("info", "work", "第四种,路由Direct模型(work)");
    }

    // 5、生产者-交换机(topic)-动态路由-队列-消费者
    @Test
    public void textTopic(){
        rabbitTemplate.convertAndSend("ect", "save.user", "第五种,动态路由Topic模型");
    }


    // 生产者(只生产不被监听消费)
    @Test
    public void publish(){
        rabbitTemplate.convertAndSend("ysc", "", "老黄牛，任劳任怨的生产！");
    }
    // 消费
    @Test
    public void ReceiveService(){
        Object received = rabbitTemplate.receiveAndConvert("ysc");
        System.out.println("手动消费:" + received);
    }


    @Test
    public void test2() {
        // RabbitTemplate只允许设置一个ConfirmCallback方法.现在我们设置了两个，执行会报错
        // 解决方法：设置RabbitTemplate的作用域@Scope("prototype")，这样每次bean都是新的
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    log.info("消息成功发送");
                } else {
                    log.info("消息发送失败");
                    log.info("错误原因" + cause);
                }
            }
        });
        // 第一个参数 交换机名称  第二个参数 routingKey 也就是路由key 第三个参数 消息
        // 填写正确的交换机名即可看见效果 如果想看见失败填写一个错误的交换机名称
        rabbitTemplate.convertAndSend("fanoutExchange", "", "什么鬼");
    }



    @Test
    public void textString(){
        rabbitTemplate.convertAndSend("cym", "cymKey", "游家纨绔");
    }
    @Test
    public void textInteger(){
        rabbitTemplate.convertAndSend("cym", "cymKey", 123);
    }
    @Test
    public void textUser(){
        User user = User.builder().id(123).name("游家纨绔").build();
        rabbitTemplate.convertAndSend("cym", "cymKey", user);
    }
}
