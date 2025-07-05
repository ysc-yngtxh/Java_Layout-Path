package org.example.listener;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitBatchConsumer {

	// 接收消息。这里监听的是基于插件的队列，这里当做是第一个消费者
	// queues：指定队列名  concurrency：设置并发消费者数量，不显性设置则默认线程数量为1
	@RabbitListener(
			queues = "batchDirectQueue",
			concurrency = "50"
	)
	public void receive(List<Message> list, Channel channel) { // Message message 消息； Channel channel 通道
		System.out.println("使用 List<Message> 接收批量消息：" + list);
		log.info("当前时间：{} -- 批量接收到消息：{}"
				, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
				, list);
		// TODO 由于在配置文件中设置了手动确认消息，因此在处理完消息后，这里需要手动确认消息。
		//      第一个参数是消息的标识（批量确认消息，确认最后一条消息的 deliveryTag，即可确认这一批消息）
		//      第二个参数是消息是否批量处理确认
		try {
			channel.basicAck(list.getLast().getMessageProperties().getDeliveryTag(), true);
		} catch (IOException e) {
			try {
				channel.basicReject(list.getLast().getMessageProperties().getDeliveryTag(), true);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}


	@RabbitListener(
			queues = "deadQueue",
			concurrency = "10"
	)
	public void receiveDeadQueue(List<Message> list, Channel channel) { // Message message 消息； Channel channel 通道
		System.out.println("使用 List<Message> 接收批量消息：" + list);
		log.error("当前时间：{} -- 批量接收到死信队列消息：{}"
				, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
				, list);
		// 由于在配置文件中设置了手动确认消息，因此在处理完消息后，需要手动确认消息。
		try {
			channel.basicAck(list.getLast().getMessageProperties().getDeliveryTag(), true);
		} catch (IOException e) {
			try {
				channel.basicReject(list.getLast().getMessageProperties().getDeliveryTag(), true);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

}
