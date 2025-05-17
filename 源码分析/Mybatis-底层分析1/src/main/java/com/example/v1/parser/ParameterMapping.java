package com.example.v1.parser;

import lombok.ToString;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-04 23:50:00
 * @apiNote TODO 占位符参数映射器
 */
@ToString
public class ParameterMapping {

	// 这个String对象，表示的是占位符 #{} 中的参数名
	private String property;

	public ParameterMapping(String property) {
		this.property = property;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
