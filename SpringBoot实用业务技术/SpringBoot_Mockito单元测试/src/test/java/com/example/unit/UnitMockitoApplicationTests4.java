package com.example.unit;

import com.example.mapper.BrandMapper;
import com.example.pojo.Brand;
import com.example.pojo.Category;
import com.example.service.impl.BrandService;
import com.example.service.impl.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.bean.override.mockito.MockReset;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UnitMockitoApplicationTests4 {

	// TODO @MockBean 和 @SpyBean 注解在 3.4.0 版本的 Spring Boot Test 中已经不推荐使用
	//  原因：@MockBean 在某些场景下存在局限性
	//       1、粒度较粗：@MockBean 注解在字段级别或类级别，它会替换 Spring 上下文中所有该类型的 Bean。
	//          在复杂的测试场景中，可能存在多个相同类型的 Bean，而我们只想模拟其中的一个特定实例。
	//       2、类型匹配的局限： @MockBean 主要通过类型进行匹配。
	//          如果存在多个相同类型的 Bean，Spring Boot Test 可能会选择到错误的 Bean 进行替换。
	//       3、与特定 Bean 实例关联不强： @MockBean 创建的 mock 对象与 Spring 上下文中原有的 Bean 实例之间的关联性不强，
	//          这在某些需要更细致控制 Bean 替换的场景下可能不够灵活。
	//  替代方案：@MockitoBean 注解
	//       1、按名称查找 Bean：除了按类型匹配，@MockitoBean 还可以通过 name 属性指定要替换的 Bean 的名称。
	//          这使得在存在多个相同类型 Bean 的情况下，可以精确地替换目标 Bean。
	//       2、@SpyBean 的功能集成： @MockitoBean 还可以通过 replace = Replace.ANY 或 replace = Replace.EXISTING
	//          结合 spy = true 来实现对现有 Bean 的 Spy 功能，即在真实 Bean 的基础上进行部分 mock。
	//       3、更灵活的策略：replace 属性允许更细致地控制 Bean 的替换行为，
	//          例如仅替换已存在的 Bean，或者如果不存在则创建新的 mock Bean

	// @MockitoBean相比@MockBean可以有更快的测试执行速度
	@MockitoBean(
			name = "categoryService",
			contextName = "",
			extraInterfaces = {EventListener.class},
			answers = Answers.RETURNS_DEEP_STUBS,
			serializable = false,
			reset = MockReset.AFTER,
			enforceOverride = true
	)
	// name             指定要替换的 Bean 的名称
	// contextName      指定要应用 Mock 的 Spring 上下文
	// extraInterfaces  为生成的 Mock 对象添加额外的接口
	// answers          定义 Mock 对象在接收到未预设（stubbed即打桩）的调用时的默认应答行为。
	//                  如：Answers.RETURNS_DEEP_STUBS 允许对链式调用进行深度 Mock，会自动创建链上的 Mock 对象
	//                  例：obj.getA().getB().getName()，整个链式下来可以确保不会出现 NullPointerException
	// serializable     指定 Mock 对象是否应该是可序列化的。设置为 true，则创建的 Mock 对象实现了 Serializable 接口。
	// reset            指定 Mock 对象应该在什么时候被重置状态（包括所有的 '打桩' 和 '调用记录'）清除回初始状态。
	//                  上述 MockReset.AFTER 表示会在每个测试方法后重置状态。
	// enforceOverride  正常情况下Mock对象会替换 Spring 上下文中对应的 Bean。
	//                  如果设置为 true，则在没有找到要替换的 Bean 时会抛出异常。
	private CategoryService categoryService;

	@MockitoSpyBean
	private BrandMapper brandMapper;
	@Autowired
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
	public void test1() {
		// @MockitoBean 模拟调用：
		// 正常来说，在没有打桩的情况下，返回值应该为类型默认值 null。
		// 但由于 answers 配置为 RETURNS_DEEP_STUBS，返回的是一个深度 mock 对象，而不是类型的默认值（如 null、0、false 等）。
		// 只有当 answers 配置为 RETURNS_DEFAULTS 时，才会返回类型默认值。
		System.err.println("@MockitoBean模拟调用：" + categoryService.findAllById(2));
		System.err.println("@MockitoBean模拟调用：" + categoryService.findAllById(2).getCategoryName());

		// @MockitoBean 模拟调用打桩。这里的效果同@Mock、@InjectMocks 一样，不同的是注解来源不同
		Mockito.when(categoryService.findAllById(2))
		       .thenReturn(new Category(2L, "小曹哇小曹～", "Cao", 0, null));
		System.err.println("@MockitoBean打桩调用：" + categoryService.findAllById(2));
	}

	@Test
	public void test2() {
		// @MockitoSpyBean 模拟调用：
		// 正常来说，在没有打桩的情况下，返回值应该为类型默认值 null。
		// 但由于 answers 配置为 RETURNS_DEEP_STUBS，返回的是一个深度 mock 对象，而不是类型的默认值（如 null、0、false 等）。
		// 只有当 answers 配置为 RETURNS_DEFAULTS 时，才会返回类型默认值。
		System.err.println("@MockitoSpyBean模拟调用：" + brandMapper.findById(2));

		// 这里使用 doReturn().when() 语法来打桩，避免真实方法调用（ORM 的查询）
		Mockito.doReturn(new Brand(2L, "小曹哇小曹～", 1, 2, "Cao"))
		       .when(brandMapper).findById(any());
		System.err.println("@MockitoSpyBean打桩调用：" + brandMapper.findById(2));
		System.err.println("@MockitoSpyBean打桩调用：" + brandService.findAllById(2));
	}

}
