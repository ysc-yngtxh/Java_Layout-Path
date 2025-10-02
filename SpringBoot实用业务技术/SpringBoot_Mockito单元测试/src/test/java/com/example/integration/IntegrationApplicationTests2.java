package com.example.integration;

import com.example.pojo.Brand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// 随机端口：使用 WebEnvironment.RANDOM_PORT 可以避免测试时的端口冲突问题。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationApplicationTests2 {

    // TestRestTemplate：适用于启动真实嵌入式服务器（如 Tomcat）的测试，
    //                   直接发起 HTTP 请求并接收响应，测试更接近于生产环境。
	@Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testEndpoint() {
        // 使用 TestRestTemplate 发起请求
        ResponseEntity<String> response =
		        restTemplate.getForEntity("/brand/test", String.class);
        
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    Assertions.assertThat(response.getBody()).isEqualTo("Hello, Spring!");
    }

    // 也可以测试 POST 请求
    @Test
    public void testCreateUser() {
        ResponseEntity<Brand> response =
		        restTemplate.postForEntity("/brand/find", 1, Brand.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getBrandName()).isEqualTo("Apple");
        Assertions.assertThat(response.getBody().getRacking()).isEqualTo(0);
    }
}
