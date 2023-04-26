package com.example;

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
@SpringBootTest(classes = Application.class)
class ApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //1、生产者-队列-消费者
    @Test
    public void textwork() {
        rabbitTemplate.convertAndSend("work","第一种,基本模型");
    }

    //2、生产者-队列-多个消费者(默认在spring AMQP实现公平调度)
    @Test
    public void texthello() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("hello","第二种,能者多劳模型");
        }
    }

    //3、生产者-交换机(广播)-队列-消费者
    @Test
    public void textfanout(){
        rabbitTemplate.convertAndSend("logs","","第三种,广播Fanout模型");
    }

    //4、生产者-交换机(广播)-路由-队列-消费者
    @Test
    public void textdirect(){
        rabbitTemplate.convertAndSend("info","error","第四种,路由Direct模型(error)");
        rabbitTemplate.convertAndSend("info","work","第四种,路由Direct模型(work)");
    }

    //4、生产者-交换机(广播)-动态路由-队列-消费者
    @Test
    public void texttopic(){
        rabbitTemplate.convertAndSend("ect","save.user","第五种,动态路由Topic模型");
    }



    //生产者(只生产不被监听消费)
    @Test
    public void publish(){
        rabbitTemplate.convertAndSend("ysc","","老黄牛，任劳任怨的生产！");
    }
    //消费
    @Test
    public void ReceiveService(){
        String receive  = (String)rabbitTemplate.receiveAndConvert("yscs");
        System.out.println(receive);
    }


    @Test
    public void test2() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * @param correlationData 相关配置信息
             * @param ack             交换机是否成功收到消息
             * @param cause           错误信息
             */
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
        //第一个参数 交换机名称  第二个参数 routingKey 也就是路由key 第三个参数 消息
        //填写正确的交换机名即可  看见效果 如果想看见失败填写一个错误的交换机名称
        rabbitTemplate.convertAndSend("cjq", "","什么鬼");
    }
}
