package com.example.v1.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-04 23:50:00
 * @apiNote TODO 占位符参数映射器
 */
@Data
@ToString
@AllArgsConstructor
public class ParameterMapping {

	// 这个String对象，表示的是占位符 #{} 中的参数名
	private String property;

}
