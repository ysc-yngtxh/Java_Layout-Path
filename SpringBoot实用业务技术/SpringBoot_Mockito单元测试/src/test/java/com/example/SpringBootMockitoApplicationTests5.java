package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
public class SpringBootMockitoApplicationTests5 {

	@Autowired
	private MockMvc mockMvc; // 注入 MockMvc

	@Test
	public void testHelloWorld() throws Exception {
		// 发送 GET 请求
		mockMvc.perform(MockMvcRequestBuilders.get("/brand/test1")
		       // 设置请求头
		       .accept(MediaType.APPLICATION_JSON))
		       // 验证响应状态码
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       // 验证响应内容
		       .andExpect(MockMvcResultMatchers.content().string("Hello, World!"));
	}
}
