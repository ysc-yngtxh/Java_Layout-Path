package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.join.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class MyController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/sendOrder")
    public ResponseEntity<String> sendDelay(Order order) {
        log.info("【订单生成时间】:{}——【1分钟后检查订单是否已经支付】:{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , order.toString());

        CorrelationData data = new CorrelationData(order.getOrderId());
        rabbitTemplate.convertAndSend("OrderExchange"
                , "Orderroutingkey"
                , JSON.toJSONString(order)  // 这里对象要转为json格式String类型，并且序列化
                , msg -> {
                    // msg.getMessageProperties().setExpiration("10*1000"); // 过期时间
                    msg.getMessageProperties().setDelay(10*1000);
                    return msg;
                }
                , data);
        return ResponseEntity.status(HttpStatus.OK).body("成功访问");
    }


    // redis重试机制
    @PostMapping("/retry")
    public ResponseEntity<String> retry(Order order){

        String orderId = order.getOrderId();
        // String message = (String)redisTemplate.opsForHash().get("test", orderId);
        String message = JSON.toJSONString(order);
        CorrelationData data = new CorrelationData(orderId);
        rabbitTemplate.convertAndSend("integrationExchange"
                , "integrationroutingkey"
                , message
                , msg -> {
                    // msg.getMessageProperties().setExpiration("10*1000"); // 过期时间
                    msg.getMessageProperties().setDelay(10*1000); // 延迟时间，这里可以不做延迟时间的，但还是先写出来，怕以后忘了
                    return msg;
                }
                , data);
        return ResponseEntity.status(HttpStatus.OK).body("成功访问");
    }
}
