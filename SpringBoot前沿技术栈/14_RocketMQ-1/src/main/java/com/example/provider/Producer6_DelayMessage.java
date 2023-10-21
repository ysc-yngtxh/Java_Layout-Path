package com.example.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 4、发送延时消息：对应场景 -- 规定时间内是否支付（外卖15min内是否支付、电影票3min内是否支付）
 *    在start版本中 延时消息一共分为18个等级分别为 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
 */
public class Producer6_DelayMessage {
   public static void main(String[] args) throws Exception {
      // 实例化一个生产者来产生延时消息
      DefaultMQProducer producer = new DefaultMQProducer("DelayMessage_Provider_Group");
      // 启动生产者
      producer.start();
      for (int i = 0; i < 10; i++) {
          // 创建消息，并指定Topic和消息体（Tag可以不指定）
          Message message = new Message("TestDelay", ("Hello scheduled message " + i).getBytes());
          // 设置延时等级3，这个消息将在 10s(等级3) 之后发送(只支持固定的几个时间,不支持自定义时间)
          message.setDelayTimeLevel(3);
          // TODO 发送消息存储到 Broker，在Broker里的每一个主题(Topic)消息默认读队列4个、写队列4个。[可以自定义队列数]
          //  循环发送的消息虽然都是相同主题，但是循环发送的消息并不是存放在一条写队列中，而是分别写入存储在4条写队列里。
          producer.send(message);
      }
       // 关闭生产者
      producer.shutdown();
  }
}