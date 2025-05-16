package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RocketOrder;
import com.example.mapper.RocketOrderMapper;
import com.example.service.RocketOrderService;
import com.example.utils.SnowFlakeUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 订单表(RocketOrder)表服务实现类
 */
@Service("rocketOrderService")
public class RocketOrderServiceImpl extends ServiceImpl<RocketOrderMapper, RocketOrder> implements RocketOrderService {

	@Autowired
	@Qualifier("defaultProvider")
	private DefaultMQProducer producer;  // 获取Common包中的普通生产者Bean

	@Autowired
	private TransactionMQProducer transactionMQProducer;

	/**
	 * 添加订单（发送消息积分模块同步添加积分）
	 **/
	@Override
	public RocketOrder addOrder(RocketOrder order) {
		order.setOrderId(SnowFlakeUtils.nextId());
		if (order.getMessageType() == 1) {
			// 普通消息
			this.save(order);
			Message message = new Message("points", "default", JSON.toJSONString(order).getBytes());
			try {
				SendResult sendResult = producer.send(message); // 同步消息
				System.out.println(
						"发送状态：" + sendResult.getSendStatus() +
								", 消息ID：" + sendResult.getMsgId() +
								", 队列：" + sendResult.getMessageQueue().getQueueId()
				                  );
				// producer.sendOneway(message); // 单向消息
				// producer.send(message, new SendCallback() { // 异步消息
				//     @Override
				//     public void onSuccess(SendResult sendResult) {
				//
				//     }
				//
				//     @Override
				//     public void onException(Throwable throwable) {
				//
				//     }
				// });
			} catch (RemotingException | MQBrokerException | InterruptedException | MQClientException e) {
				e.printStackTrace();
			}
		} else {
			// 事务消息
			Message message = new Message("points", "transaction", JSON.toJSONString(order).getBytes());
			try {
				transactionMQProducer.sendMessageInTransaction(message, null);
			} catch (MQClientException e) {
				e.printStackTrace();
			}
		}
		return order;
	}
}
