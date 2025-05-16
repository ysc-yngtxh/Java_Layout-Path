package com.example.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MyService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@RabbitListener(queues = "orderQueue")
	public void process(String orders, Message message, Channel channel) {
		try {
			// TODO 具体业务
			// spring_returned_message_correlation固定写法，获取 CorrelationData 的Id值
			// 存在两个属性：spring_returned_message_correlation：该属性是指退回待确认消息的唯一标识
			//             spring_listener_return_correlation：该属性是用来确定消息被退回时调用哪个监听器
			String msgId = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
			System.out.println("获取消息关联Id：" + msgId);

			// 这里获取 Redis 的 key 为"order"的数据，并判断其数据中 key 部分是否包含 msgId
			if (stringRedisTemplate.opsForHash().entries("order").containsKey(msgId)) {
				// redis 中包含该 key，说明该消息已经被消费过,这个判断就是为了避免消息被重复消费。

				log.info("correlationId值为--{}--的消息已经被消费，为避免重复消费，这次消息将会被拒绝消费", msgId);
				stringRedisTemplate.expire("order", 60, TimeUnit.SECONDS);
				// 消息已消费,拒绝再次消费。 消息id  为true的话就是将小于消息id的消息都拒绝  是否重新排进队列
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
				return;
			}

			log.info("手动确认消息:{}", orders);
			// 添加到redis。参数1、Key 2、HashKey（相当于redis根据Key取出的Map中的key） 3、Map 中的 value
			stringRedisTemplate.opsForHash().put("order", msgId, orders);
			stringRedisTemplate.expire("order", 360, TimeUnit.SECONDS);
			log.info(String.valueOf(message.getMessageProperties().getDeliveryTag()));
			// 手动确认消息消费。参数：1、消息通道标识Id  2、为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RabbitListener(queues = "integrationQueue")
	@Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 2))
	public void processHandler(String msg, Message message, Channel channel) throws Exception {

		log.info(new String(message.getBody()));
		// TODO 具体业务
		String msgId = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
		if (msg != null) {
			// 说明redis中即将要删除的数据存入到了rabbitmq的队列中
			log.info("redis中即将要删除的数据：{}", msgId);
			stringRedisTemplate.opsForHash().delete("test", msgId);
			// TODO 更新数据库。。。
			if (stringRedisTemplate.opsForHash().entries("test").containsKey(msgId) && "数据库中数据更新好了".equals(null)) {

			}
			// 添加到redis。参数1、Key 2、HashKey（相当于redis根据Key取出的Map中的key） 3、value
			stringRedisTemplate.opsForHash().put("test", msgId, msg);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);  // 确认消息已消费
			int i = 1 / 0; // 这里模拟出现异常导致重试机制触发
		}
	}

	@Recover  // 注意这里方法的返回值类型一定要和上面的一致
	public void recover(Exception e) {
		log.error("达到最大重试次数，或抛出了一个没有指定进行重试的异常 {}", e.getMessage());
		log.error("消息确认成功了，消息才会移除，确认成功后不管后面是异常还是断开服务，消息已经被移除了。" +
				          "如果在确认之前抛出异常，消息不会移除也不会重试，监听程序会因为异常停止不再处理消息，" +
				          "如果此时断开服务，消息重新回到队列");
	}

	@RabbitListener(queues = "simpleDeadQueue")
	public void ysc(Message message, Channel channel) throws IOException {
		log.info("死信中有数据：{}", message);
		// 配置文件中已经设置了消息确认改为手动，因此当我消费完了就手动调用ack确认方法即可。
		// 否则就会出现我虽然拿到了消息但并没有进行消费，造成死信队列的消息堆积
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
