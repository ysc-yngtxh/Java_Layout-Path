package com.example.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-21 18:00
 * @apiNote TODO 发送集合消息
 */
@SpringBootTest(classes = Demo3_ListAndBatchMessage.class)
public class Demo3_ListAndBatchMessage {

	// 发送集合消息
	@Test
	public void ListMessage() throws Exception {
		// 实例化消息生产者 -- 生产组(ListMessage_Group)
		DefaultMQProducer producer = new DefaultMQProducer("ListMessage_Group");
		// 设置NameServer的地址
		producer.setNamesrvAddr("localhost:9876");
		// 启动Producer实例
		producer.start();
		ArrayList<Message> list = new ArrayList<>();
		list.add(new Message("TopicList", "TagA", ("Hello TagA").getBytes(StandardCharsets.UTF_8)));
		list.add(new Message("TopicList", "TagB", ("Hello TagB").getBytes(StandardCharsets.UTF_8)));
		list.add(new Message("TopicList", "TagC", ("Hello TagC").getBytes(StandardCharsets.UTF_8)));
		// TODO 发送 Collection集合 消息到Broker。发送的 Collection集合 消息被存储在同一个队列里。
		//  可以这么理解：每执行一次send()方法，相当于重新在MQ规则下选择队列再写入存储消息。
		SendResult sendResult = producer.send(list);
		System.out.printf("RocketMQ 消息发送到Broker，Broker会将消息进行持久化处理。" +
				                  "持久化成功后，Broker给生产者响应消息写入结果（ACK响应）。通过返回的结果判断是否成功送达。" +
				                  "返回的结果为：%s %n", sendResult.getSendStatus());
		System.out.printf("%s %n", sendResult);      // 打印返回结果
		// 如果不再发送消息，关闭Producer实例。
		producer.shutdown();
	}

	// 消费消息
	public static void main(String[] args) throws MQClientException {
		// 实例化消息Push消费者 -- 消费组【注意：消费分组不必与生产分组保持一致】
		DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("ListMessage_Group");
		// 设置NameServer的地址
		pushConsumer.setNamesrvAddr("localhost:9876");
		// 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
		// 如果不是第一次启动，那么按照上次消费的位置继续消费
		pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
		// 设置消费模型，集群还是广播，默认为集群
		pushConsumer.setMessageModel(MessageModel.CLUSTERING);
		// 消费者最小线程量
		pushConsumer.setConsumeThreadMin(5);
		// 消费者最大线程量
		pushConsumer.setConsumeThreadMax(10);
		// 控制消费者每次从 Broker 请求拉取的消息数量（网络传输层），默认是32
		pushConsumer.setPullBatchSize(32);
		// 控制传递给消费监听器 (MessageListener) 的批量消息数量（业务处理层），默认是1
		pushConsumer.setConsumeMessageBatchMaxSize(1);

		// 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
		// pushConsumer.subscribe("TopicList", "*");
		// TODO Tag是一个简单而有用的设计，其可以来选择您想要的消息。
		pushConsumer.subscribe("TopicList", "TagA || TagB || TagC");

		// pushConsumer.registerMessageListener() 注册消息监听器
		// MessageListenerConcurrently 并发模式，多线程的。相当于多线程去处理从broker拉取回来的消息
		// MessageListenerOrderly 顺序模式，单线程的。单线程去处理从broker拉取回来的消息
		pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
				// MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型（我建议还是指定类型较方便）
				// System.out.printf：支持使用字符信息的格式化
				System.out.printf("%s 接收队列%s的新消息: %s %n",
				                  Thread.currentThread().getName(), list.stream().map(MessageExt::getQueueId).toList(),
				                  list.stream().map(MessageExt::getBody).map(String::new).toList());
				// 标记该消息已经被成功消费
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		// 启动消费者实例
		pushConsumer.start();
		System.out.printf("Consumer Started.%n");
	}
}
