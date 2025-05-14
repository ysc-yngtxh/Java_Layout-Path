package com.example.convert;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-15 01:15:30
 */
public class CustomConvert implements Converter<String, Object> {

	@Override
	public Object convert(String s) {
		return JSON.parse(s);
	}
}
