package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-07-10 21:30:00
 * @apiNote TODO 自定义分页包装类
 */
@Data
@AllArgsConstructor
public class PageVo {

	private Object data;
	private Integer total;

	public static PageVo info(Object data, Integer total) {
		return new PageVo(data, total);
	}
}
