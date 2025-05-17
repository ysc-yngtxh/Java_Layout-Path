package com.example;

import com.example.entity.Province;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {

	public static void main(String[] args) throws JsonProcessingException {
		// 使用Jackson把Java对象转为json格式的字符串
		Province p = new Province();
		p.setId(1);
		p.setName("河北");
		p.setJiancheng("冀");
		p.setShenghui("石家庄");

		// 使用Jackson把p转为json
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(p);
		System.out.println("转换的json==" + json);
	}

}
