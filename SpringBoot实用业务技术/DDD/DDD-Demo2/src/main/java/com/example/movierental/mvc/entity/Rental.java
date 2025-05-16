package com.example.movierental.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO 租赁信息
 */
@Data
@AllArgsConstructor
public class Rental {

	/**
	 * 租的电影
	 */
	private Movie movie;

	/**
	 * 已租天数
	 */
	private int daysRented;
}
