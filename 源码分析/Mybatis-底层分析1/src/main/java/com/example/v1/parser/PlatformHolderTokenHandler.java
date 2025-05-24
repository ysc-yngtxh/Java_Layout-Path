package com.example.v1.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-04 23:50:00
 * @apiNote TODO 动态参数处理器
 */
public class PlatformHolderTokenHandler implements TokenHandler {

	// 当前这个List里面存的是 ParameterMapping对象，每个 ParameterMapping对象 代表一个 #{} 参数映射
	private final List<ParameterMapping> parameterMappings = new ArrayList<>();

	public List<ParameterMapping> getParameterMappings() {
		return this.parameterMappings;
	}

	@Override
	public String handleToken(String content) {
		this.parameterMappings.add(new ParameterMapping(content));
		return "?";
	}

}
