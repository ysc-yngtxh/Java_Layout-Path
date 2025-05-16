package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.RocketOrder;
import com.example.service.RocketOrderService;
import com.example.vo.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 订单表(RocketOrder)表控制层
 */
@RestController
@RequestMapping("rocketOrder")
public class RocketOrderController {

	@Autowired
	private RocketOrderService orderService;

	/**
	 * 新增订单
	 **/
	@PostMapping
	public Result<RocketOrder> addOrder(@RequestBody RocketOrder order) {
		return Result.success(orderService.addOrder(order));
	}

	/**
	 * 根据用户id查询订单列表
	 **/
	@GetMapping("/{userId}")
	public Result<List<RocketOrder>> getOrders(@PathVariable Integer userId) {
		QueryWrapper<RocketOrder> pointsQueryWrapper = new QueryWrapper<>();
		pointsQueryWrapper.lambda().eq(RocketOrder::getUserId, userId);
		return Result.success(orderService.list(pointsQueryWrapper));
	}
}
