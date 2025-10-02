package com.example.unit;

import com.example.mapper.BrandMapper;
import com.example.pojo.Brand;
import com.example.pojo.Category;
import com.example.service.impl.BrandService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
// 这里我们只是做单元测试，无需Spring 的上下文环境，因此无需添加 @SpringBootTest 注解
// 可以通过 @ExtendWith 注解，启用 Mockito 的扩展功能：如 @Mock、@Spy、@InjectMocks 注解的支持
public class UnitMockitoApplicationTests2 {

	// @Mock：用于代替 Mockito.mock() 创建mock对象。
	//        Mock会将目标对象整个模拟，所有方法默认都返回null，并且原方法中的代码逻辑不会执行，
	//        被Mock出来的对象，想用哪个方法，哪个方法就需要打桩，否则返回null；
	// @Spy：用于代替 Mockito.spy() 创建spy对象。
	//       Spy可实现对目标对象部分方法、特定入参条件时的打桩，没有被打桩的方法，将会真实调用。
	// @InjectMocks：创建一个实例，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。

	// @Mock 模拟调用，这不是一个真正的对象，并且不维护状态，不存在更改，无法注入到Spring上下文中
	@Mock
	private BrandMapper brandMapper;
	// @Spy 真实调用，标注该注解的对象能够调用所监视对象的所有正常方法，但无法注入到 Spring 容器上下文中使用
	@Spy
	private List<Category> list = new ArrayList<>();
	// 使用 @InjectMocks 注解，Mockito会自动将使用 @Mock 或 @Spy 注解创建的模拟对象注入到被测试类的对应字段中。
	@InjectMocks
	private BrandService brandService;


	// 被 @BeforeEach 标注的方法会在每个测试方法执行前被调用，执行一些初始化操作，如初始化变量、设置mock对象等
	@BeforeEach
	public void init() {
		// MockitoAnnotations.openMocks(this);
		// 上述方法会扫描当前测试类中的所有 @Mock、@Spy、@InjectMock 注解，并为它们创建相应的模拟对象。
		// 目前最新的 SpringBoot Test 版本中，已经不需要手动调用该方法了。建议使用 @ExtendWith(MockitoExtension.class)
		System.out.println("初始化方法");
	}

	@Test
	public void test() {
		// @Mock 模拟调用，因此不会真正的调用 ORM 框架的查询方法，结果返回 类型默认值
		System.err.println("@Mock模拟调用：" + brandMapper.findById(1));

		// 使用 @InjectMocks 注解，Mockito会自动将使用 @Mock 或 @Spy 注解创建的模拟对象或注入到被测试类的对应字段中。
		System.err.println("注入的模拟对象不打桩，返回值为类型默认值：" + brandService.findAllById(1));

		// 使用 @InjectMocks 注解，Mockito会自动将使用 @Mock 或 @Spy 注解创建的模拟对象或注入到被测试类的对应字段中。
		Mockito.when(brandMapper.findById(1))
		       .thenReturn(new Brand(1L, "华为 meta", 1, 0, null));
		System.err.println("注入的模拟对象打桩，返回值为打桩返回值：" + brandService.findAllById(1));

		// @Spy 真实调用，会真实的调用其对象的方法【如 List 集合中的 add()方法】，但是无法注入到 Spring 容器中。
		list.add(new Category(3L, "小曹哇小曹～", "Cao", 0, null));
		System.err.println("@Spy 真实调用：" + list);
	}
}
