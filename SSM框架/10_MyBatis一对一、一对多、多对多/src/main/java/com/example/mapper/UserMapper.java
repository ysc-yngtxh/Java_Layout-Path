package com.example.mapper;

import com.example.pojo.User;

public interface UserMapper {

	/* 查询id=? 的用户以及拥有的订单
	 *     select *
	 *     from ssm_user u
	 *       inner join ssm_order o
	 *         on u.id = o.uid
	 *     where u.id = ?;
	 *  1. 参数类型: Integer
	 *  2. 返回值类型: User
	 */
	User UserWithOrders(Integer uid);
}
