package com.example.pojo;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 */
public class User implements Serializable {
	@Serial
	private static final long serialVersionUID = -2605710122888070653L;

	private Integer id;
	private String userName;
	private String birthDay;
	private String sex;
	private String address;

	private List<Order> orderList; // 一个用户可以拥有多个订单

	/* 1. mybatis看问题的角度不同
	 *     a. 从user表的角度看问题 : 一对多(一个用户有多个订单)
	 *     b. 从orders表的角度看问题 : 一对一(一个订单只能属于一个用户)
	 *
	 * 2. mybatis解决问题的方式不同
	 *     a. sql中是用外键建立表关系
	 *     b. mybatis中用属性
	 */

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", birthDay='" + birthDay + '\'' +
				", sex='" + sex + '\'' +
				", address='" + address + '\'' +
				", orderList=" + orderList +
				'}';
	}
}
