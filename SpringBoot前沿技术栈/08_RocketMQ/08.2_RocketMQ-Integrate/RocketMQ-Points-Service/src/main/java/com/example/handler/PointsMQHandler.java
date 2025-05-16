package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.RocketPoints;
import com.example.mq.annotation.MQHandlerActualizer;
import com.example.mq.handler.MQHandler;
import com.example.service.RocketPointsService;
import com.example.utils.SnowFlakeUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 积分MQ
 */
@MQHandlerActualizer(topic = "points")
public class PointsMQHandler implements MQHandler {

	@Autowired
	private RocketPointsService pointsService;

	@Override
	public ConsumeConcurrentlyStatus handle(String tag, MessageExt messageExt) {
		// 消息监听
		String messageStr = new String(messageExt.getBody());
		Map orderMap = (Map) JSON.parse(messageStr);
		RocketPoints points = new RocketPoints();
		Long orderId = (Long) orderMap.get("orderId");
		System.out.println("消息tag为：" + tag);
		System.out.println("消息监听：" + "Points为订单" + orderId + "添加积分");
		// 查询该订单是否已经生成对应积分（rocketMQ可能会重复发送消息，需实现幂等）
		QueryWrapper<RocketPoints> pointsQueryWrapper = new QueryWrapper<>();
		pointsQueryWrapper.lambda().eq(RocketPoints::getOrderId, orderId);
		RocketPoints tempPoints = pointsService.getOne(pointsQueryWrapper);
		if (tempPoints != null) {
			// 该订单已经生成积分
			System.out.println(orderId + "已经生成积分");
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		points.setPointsId(SnowFlakeUtils.nextId());
		points.setOrderId(orderId);
		Integer orderAmout = (Integer) orderMap.get("orderAmout");
		points.setPoints(orderAmout * 10);
		pointsService.save(points);
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
