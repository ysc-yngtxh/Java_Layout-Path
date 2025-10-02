package com.example.integration;

import com.example.pojo.Brand;
import com.example.service.impl.BrandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// WebEnvironment.MOCK          是默认值，可省略。提供模拟的 Servlet 环境，不启动真实服务器。依赖 @AutoConfigureMockMvc。	使用 MockMvc 进行控制层测试，速度较快
// WebEnvironment.RANDOM_PORT	启动嵌入式服务器并监听随机端口。	使用 TestRestTemplate 或 WebTestClient 进行测试
// WebEnvironment.DEFINED_PORT	使用 application.properties 中定义的端口（或默认的 8080）启动服务器。	需要固定端口的测试场景
@AutoConfigureMockMvc // 自动配置 MockMvc 实例，用于模拟 MVC 请求。通常与 WebEnvironment.MOCK 结合使用。
public class IntegrationApplicationTests {

	// 使用 MockMvc (模拟 MVC 环境)
	// 这种方法不会启动真正的服务器，而是模拟 Servlet 环境，测试速度较快。
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean // 模拟 BrandService 这个 Bean，真实的 BrandService 不会被调用
	private BrandService brandService;


	// 集成测试：使用 MockMvc 模拟 HTTP 请求，并验证响应结果
	@Test
	public void test1() throws Exception {
		// 发送 GET 请求
		mockMvc
			   .perform(MockMvcRequestBuilders
					            .get("/brand/test")
				                .param("name", "World")
				                .accept(MediaType.APPLICATION_JSON)
		       )
		       // 验证响应状态码
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       // 验证响应内容
		       .andExpect(MockMvcResultMatchers.content().string("Hello, World!"));
	}

	@Test
	public void test2() throws Exception {
		// 发送 POST 请求
		mockMvc
			   .perform(MockMvcRequestBuilders
					            .post("/brand/find")
				                .accept(MediaType.APPLICATION_JSON)
		       )
		       // 验证响应状态码
		       .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void test3() throws Exception {
		Brand inputBrand = new Brand("Nike", 0, 0, "Just Do It");
		// 假设保存后有了 ID
		Brand saveBrand = new Brand(1L, "Nike", 0, 0, "Just Do It");

		// 模拟 brandService.add() 方法的行为
		when(brandService.add(any(Brand.class))).thenReturn(saveBrand);

		mockMvc.perform(MockMvcRequestBuilders
				                .post("/brand/add")
		                        .contentType(MediaType.APPLICATION_JSON)
		                        .content(objectMapper.writeValueAsString(inputBrand))
		       )
		       .andExpect(status().isCreated())
		       // jsonPath 用于验证 JSON响应体 中的具体字段值
		       .andExpect(jsonPath("$.id").value(1))
		       .andExpect(jsonPath("$.brandName").value("Nike"))
		       .andExpect(jsonPath("$.racking").value(0))
		       .andExpect(jsonPath("$.deleteFlag").value(0))
		       .andExpect(jsonPath("$.remark").value("Just Do It"));
	}
}
