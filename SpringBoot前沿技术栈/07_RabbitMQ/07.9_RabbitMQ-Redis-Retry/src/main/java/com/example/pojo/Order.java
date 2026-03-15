package com.example.pojo;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
	@Serial
	private static final long serialVersionUID = -2378574269516834695L;

	private String orderId;      // 订单id

	private Integer orderStatus; // 订单状态 0：未支付，1：已支付，2：订单已取消

	private String orderName;    // 订单名字

}
