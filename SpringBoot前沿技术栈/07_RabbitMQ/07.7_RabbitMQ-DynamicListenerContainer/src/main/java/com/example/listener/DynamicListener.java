package com.example.listener;

import com.rabbitmq.client.Channel;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.util.ErrorHandler;

/**
 * 队列监听类（动态）
 */
@Slf4j
@Configuration
public class DynamicListener {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 消息监听容器。使用 SimpleMessageListenerContainer 实现动态监听
	 *
	 * @param connectionFactory 连接工厂
	 */
	@Bean
	public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		// 监听消息的消费逻辑
		container.setMessageListener(CustomMessageHandler());

		// 使用内置错误处理器：处理消息在消费过程中的异常
		// container.setErrorHandler(new ConditionalRejectingErrorHandler());
		// 自定义消息消费过程中的异常处理
		container.setErrorHandler(errorHandlerToDead());

		// 添加重试拦截器
		container.setAdviceChain(retryInterceptor());

		return container;
	}

	/**
	 * SimpleMessageListenerContainer监听容器在监听到有消息发布后的处理逻辑
	 */
	@Bean
	public ChannelAwareMessageListener CustomMessageHandler() {
		return new ChannelAwareMessageListener() {
			@Override
			public void onMessage(Message message, Channel channel) throws Exception {
				try {
					// 模拟消息在消费中的处理逻辑（抛出异常）
					log.info("SimpleMessageListenerContainer，动态监听器收到消息: {}", new String(message.getBody()));
					throw new RuntimeException("消息处理失败");
				} catch (Exception e) {
					// 拒绝消息并设置 requeue=false，表示拒绝重新入队，触发死信队列
					// 需要注意的是：要在配置文件中开启手动确认模式后，才能使用此方法手动拒绝消息
					channel.basicReject(
							message.getMessageProperties().getDeliveryTag(),
							false
					                   );
					throw e; // TODO 可选：继续抛出异常供重试策略 retryInterceptor 处理
				}
			}
		};
	}

	/**
	 * 消息消费异常后的重试策略
	 */
	@Bean
	public RetryOperationsInterceptor retryInterceptor() {
		return RetryInterceptorBuilder.stateless()
		                              // 最大重试次数（包括首次调用）
		                              .maxAttempts(3)
		                              // 初始间隔1秒，倍数2，最大间隔5秒
		                              .backOffOptions(1000, 2.0, 5000)
		                              // 重试失败后的策略：
		                              // RejectAndDontRequeueRecoverer：重试耗尽后，直接reject，丢弃消息。默认就是这种方式
		                              // ImmediateRequeueMessageRecoverer：重试耗尽后，返回nack，消息重新入队
		                              // RepublishMessageRecoverer：重试耗尽后，将失败消息投递到指定的交换机(这里是投递到死信)
		                              .recoverer(new MessageRecoverer() {
			                              @Override
			                              public void recover(Message message, Throwable cause) {
				                              // TODO 1. 转发消息到死信队列
				                              new RepublishMessageRecoverer(rabbitTemplate, "deadExchange", "deadRoutingKey")
						                              .recover(message, cause);
				                              // TODO 2. 重试耗尽后消息会被转发或拒绝，但异常会被标记为已处理，就不会传播到 ErrorHandler。
				                              //         因此，这里我们抛出异常供 ErrorHandler 捕获
				                              throw new ListenerExecutionFailedException("重试耗尽", cause, message);
			                              }
		                              })
		                              .build();
	}

	/**
	 * 消息在消费过程中出现异常时的处理逻辑
	 */
	private ErrorHandler errorHandlerToDead() {
		return new ErrorHandler() {
			@Override
			public void handleError(@NonNull Throwable throwable) {
				// 提取原始异常（如果是 ListenerExecutionFailedException）
				if (throwable instanceof ListenerExecutionFailedException) {
					ListenerExecutionFailedException ex = (ListenerExecutionFailedException) throwable;
					// 消费失败的消息
					Message failedMessage = ex.getFailedMessage();
					log.error("SimpleMessageListenerContainer监听到消息处理失败，异常原因: {}。消息内容：{}"
							, throwable.getCause().getMessage()
							, new String(failedMessage.getBody())
					         );
				} else {
					log.error("SimpleMessageListenerContainer监听器发生未知错误: {}", throwable.getMessage());
				}
			}
		};
	}
}
