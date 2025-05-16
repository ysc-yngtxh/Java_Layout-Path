package com.example.movierental.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO 电影信息
 */
@Data
@AllArgsConstructor
public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private String title;
	private Integer priceCode;
}
