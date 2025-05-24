package com.example.v3.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ParameterMapping {

	// 这个String对象，表示的是占位符 #{} 中的参数名
	private String property;

}
