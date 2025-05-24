package com.example.v3.parser;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class ParameterMappingTokenHandler implements TokenHandler {

	// 当前这个List里面存的是ParameterMapping对象，每个ParameterMapping对象代表一个 #{} 参数映射
	@Getter
	private final List<ParameterMapping> parameterMappings = new ArrayList<>();

	public String handleToken(String content) {
		this.parameterMappings.add(new ParameterMapping(content));
		return "?";
	}

}
