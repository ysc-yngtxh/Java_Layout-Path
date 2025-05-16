package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.RocketPoints;
import com.example.service.RocketPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 积分表(RocketPoints)表控制层
 */
@RestController
@RequestMapping("rocketPoints")
public class RocketPointsController {

	@Autowired
	private RocketPointsService pointsService;

	@GetMapping("/{orderId}")
	public RocketPoints getPointsByOrderId(@PathVariable String orderId) {
		QueryWrapper<RocketPoints> pointsQueryWrapper = new QueryWrapper<>();
		pointsQueryWrapper.lambda().eq(RocketPoints::getOrderId, orderId);
		return pointsService.getOne(pointsQueryWrapper);
	}
}
