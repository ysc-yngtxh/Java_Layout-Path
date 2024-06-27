package com.example;

import com.example.mapper.BrandMapper;
import com.example.mapper.CategoryMapper;
import com.example.mapper.SkuMapper;
import com.example.pojo.Brand;
import com.example.pojo.Category;
import com.example.service.BrandService;
import com.example.service.CategoryService;
import com.example.service.SkuService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@Slf4j
@SpringBootTest
public class SpringBootMockitoApplicationTests2 {

	// @Mock：用于代替 Mockito.mock 创建mock对象，这个对象是一个“假”对象，它不会执行任何实际的方法逻辑。
	//        所有方法调用都会被模拟，除非你明确地设置了某些方法的行为。创建一个Mock实例，需要基于JUnit5环境。
	// @Spy：创建的是被测类的“真实”实例的一个包装。实际上会执行被包裹对象的真实方法，除非你明确告诉它要模拟某个特定的方法行为。
	// @InjectMocks：创建一个实例，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。

	// @spy和@mock生成的对象不受spring管理
	// @spy调用真实方法时，其它bean是无法注入的，要使用注入，要使用@SpyBean
	// @SpyBean和@MockBean生成的对象受spring管理，相当于自动替换对应类型bean的注入，比如@Autowired等注入

	// @Mock和@MockBean生成的对象对于未进行打桩的方法，默认不执行，有返回值的，返回默认值（null,0,false）

	// @Mock 模拟调用（Mockito 的注解）。无法注入到Spring上下文中
	@Mock
	private BrandMapper brandMapper;

	// 使用 @InjectMocks 注解，Mockito会自动将使用 @Mock 或 @Spy 注解创建的模拟对象注入到被测试类的对应字段中。
	@InjectMocks
	private BrandService brandService;


	// @MockBean 模拟调用（SpringBoot 的注解）。在Spring上下文中创建一个模拟对象，并将其注入到被测试对象中。
	@MockBean
	private CategoryMapper categoryMapper;

	@Autowired
	private CategoryService categoryService;


	// @Spy 真实调用，标注该注解的测试类无法注入到 Spring 容器上下文中使用
	@Spy
	private List<Category> list = new ArrayList<>();

	// @SpyBean 真实调用，标注该注解的测试类可以注入到 Spring 容器上下文中使用
	@SpyBean
	private SkuMapper skuMapper;

	// 使用 @InjectMocks 注解，Mockito会自动将使用 @Mock 或 @Spy 注解创建的模拟对象注入到被测试类的对应字段中。
	@InjectMocks
	private SkuService skuService;


	// 被 @BeforeEach 标注的方法会在每个测试方法执行前被调用
	@BeforeEach
	public void init() {
		// TODO 对于 @Mock 或 @Spy 注解定义的所有模拟对象能够被正确地创建和初始化。
		MockitoAnnotations.openMocks(this);
		// 执行一些初始化操作，如初始化变量、设置mock对象等
		System.out.println("初始化方法");
	}

	@Test
	public void test1() {
		// @Mock 模拟调用，因此不会真正的调用 ORM 框架的查询方法，结果返回 类型默认值（null，0，false）
		System.err.println("@Mock模拟调用：" + brandMapper.findById(1));

		// 使用 @InjectMocks 注解，Mockito会自动将使用 @Mock 或 @Spy 注解创建的模拟对象或注入到被测试类的对应字段中。
		System.err.println("注入的模拟对象不打桩，返回值为默认值null：" + brandService.findBrandById(1));

		// 使用 @InjectMocks 注解，Mockito会自动将使用 @Mock 或 @Spy 注解创建的模拟对象或注入到被测试类的对应字段中。
		Mockito.when(brandMapper.findById(1))
				.thenReturn(new Brand(1L, "华为 meta", 1, 0, null));
		System.err.println("注入的模拟对象打桩，返回值为打桩返回值：" + brandService.findBrandById(1));
	}

	@Test
	public void test2() {
		// @MockBean 模拟调用
		System.err.println("@MockBean模拟调用：" + categoryMapper.findById(2));

		// 由于，我们已经使用了@MockBean。所以已经在Spring上下文中注入了模拟对象categoryMapper。
		// 因此不会执行 ORM 框架的方法。而模拟对象又没有打桩，因此这里打印值为 null
		System.err.println("@MockBean模拟调用：" + categoryService.findCategoryById(2));

		// @MockBean 模拟调用打桩。这里的效果同@Mock、@InjectMocks 一样，不同的是注解来源不同
		Mockito.when(categoryMapper.findById(2))
				.thenReturn(new Category(2L, "小曹哇小曹1～", "Cao", 0, null));
		System.err.println(categoryService.findCategoryById(2));
	}

	@Test
	public void test3() {
		// @Spy 真实调用，会真实的调用其对象的方法，但是无法注入到 Spring 容器中。因此适合用来标注集合这种原生的类
		list.add(new Category(3L, "小曹哇小曹～", "Cao", 0, null));
		System.err.println("@Spy 真实调用：" + list);

		// @SptBean 真实调用。可以注入到 Spring 容器中。所以能使用 ORM 框架查询数据库数据
		System.err.println("@SptBean 真实调用：" + skuMapper.findById(30));

		System.err.println("@InjectMocks 真实调用：" + skuService.findSkuById(3));
	}
}
