package com.example.v2.parser;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderTokenHandler implements TokenHandler {

	// 当前这个List里面存的是ParameterMapping对象，每个ParameterMapping对象代表一个 #{} 参数映射
	private final List<ParameterMapping> parameterMappings = new ArrayList();

	public List<ParameterMapping> getParameterMappings() {
		return this.parameterMappings;
	}

	public String handleToken(String content) {
		this.parameterMappings.add(new ParameterMapping(content));
		return "?";
	}

}
