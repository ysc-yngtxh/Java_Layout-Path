package com.example;

import com.example.mapper.BrandMapper;
import com.example.mapper.CategoryMapper;
import com.example.mapper.SkuMapper;
import com.example.pojo.Brand;
import com.example.pojo.Category;
import com.example.pojo.Sku;
import com.example.service.impl.BrandService;
import com.example.service.impl.CategoryService;
import com.example.service.impl.SkuService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@Slf4j
// 由于使用了 @MockBean、@SpyBean 等注解，需要在 Spring 容器中运行测试，因此要添加 @SpringBootTest 注解
@SpringBootTest
// @ExtendWith 注解是用来启用 Mockito 的扩展功能：如 @Mock、@Spy、@InjectMocks 注解的支持
@ExtendWith(MockitoExtension.class)
public class SpringBootMockitoApplicationTests3 {

	// TODO
	//  @Mock 和 @Spy 注解是纯 Mockito 框架的注解，无法注入到 Spring 容器中，因此不适合在 Spring Boot 测试中使用
	//  在 Spring Boot 测试中，统一使用 @MockBean 和 @SpyBean，避免混合使用 Mockito 的 @Mock 和 @Spy。

	@Autowired
	private CategoryService categoryService;
	// @MockBean 注解基于 Spring 环境生效，
	//    用于在 Spring 应用上下文中创建一个 Mockito mock 模拟对象，并替换掉原有类型的 Bean。
	@MockBean
	private CategoryMapper categoryMapper; // 替换 Spring 上下文中的 CategoryMapper


	@Autowired
	private BrandService brandService;
	// @SpyBean 注解基于 Spring 环境生效，
	//    用于在 Spring 应用上下文中创建一个 Mockito spy 对象，它包装了真实的 Bean，即可以模拟又能真实调用方法。
	@SpyBean
	private BrandMapper brandMapper; // 包装真实的 BrandMapper


	@SpyBean
	private SkuService skuService;
	@SpyBean
	private SkuMapper skuMapper;


	// 被 @BeforeEach 标注的方法会在每个测试方法执行前被调用，执行一些初始化操作，如初始化变量、设置mock对象等
	@BeforeEach
	public void init() {
		// MockitoAnnotations.openMocks(this);
		// 上述方法会扫描当前测试类中的所有 @Mock、@Spy、@InjectMock 注解，并为它们创建相应的模拟对象。
		// 目前最新的 SpringBoot Test 版本中，已经不需要手动调用该方法了。建议使用 @ExtendWith(MockitoExtension.class)
		System.out.println("初始化方法");
	}

	@Test
	public void test1() {
		// @MockBean 模拟调用
		System.err.println("@MockBean模拟调用：" + categoryMapper.findById(2));

		// 由于，我们已经使用了@MockBean。所以已经在Spring上下文中注入了模拟对象categoryMapper。
		// 因此不会执行 ORM 框架的方法。而模拟对象又没有打桩，因此这里打印值为 null
		System.err.println("@MockBean模拟调用：" + categoryService.findAllById(2));

		// @MockBean 模拟调用打桩。这里的效果同@Mock、@InjectMocks 一样，不同的是注解来源不同
		Mockito.when(categoryMapper.findById(2))
		       .thenReturn(new Category(2L, "小曹哇小曹～", "Cao", 0, null));
		System.err.println(categoryService.findAllById(2));
	}

	@Test
	public void test2() {
		// @SpyBean 真实调用。可以注入到 Spring 容器中。所以能使用 ORM 框架查询数据库数据
		System.err.println("@SpyBean brandMapper 真实调用：" + brandMapper.findById(2));
		System.err.println("@SpyBean brandService 真实调用：" + brandService.findAllById(2));

		// @SpyBean 模拟调用打桩
		Mockito.doReturn(new Brand(3L, "小曹哇小曹～", 2, 1, "Cao"))
		       .when(brandMapper).findById(3);
		System.err.println("@SpyBean brandMapper 打桩调用：" + brandMapper.findById(3));
		System.err.println("@SpyBean brandService 打桩调用：" + brandService.findAllById(3));
	}

	@Test
	public void test3() {
		// @SpyBean 真实调用。可以注入到 Spring 容器中。所以能使用 ORM 框架查询数据库数据
		System.err.println("@SpyBean 真实调用：" + skuMapper.findById(30));
		System.err.println("@SpyBean 真实调用：" + skuService.findAllById(3));

		// @SpyBean 模拟调用打桩
		Mockito.doReturn(new Sku(30, 3, "http://www.baidu.com", "小曹哇小曹～", new BigDecimal("3000"), "{}"))
		       .when(skuMapper).findById(30);
		System.err.println("@SpyBean 打桩调用：" + skuMapper.findById(30));
		System.err.println("@SpyBean 打桩调用：" + skuService.findAllById(30));
	}
}
