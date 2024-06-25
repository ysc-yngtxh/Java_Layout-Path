package com.example;

import com.example.mapper.CategoryMapper;
import com.example.mapper.SkuMapper;
import com.example.service.PositionService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
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
	//
	// spy和mock的相同点和区别：
	// 		1.得到的对象同样可以进行“监管”，即验证和打桩。
	// 		2.如果不对spy对象的methodA打桩，那么调用spy对象的methodA时，会调用真实方法。
	// 		3.如果不对mock对象的methodA打桩，将doNothing，且返回默认值（null,0,false）。

	// 模拟调用
	@Mock
	private CategoryMapper categoryMapper;

	@Spy
	private List<Object> list = new ArrayList<>();

	// 真实调用
	@SpyBean
	private SkuMapper skuMapper;

	@InjectMocks
	private PositionService positionService;

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
		// @Mock 模拟调用
		System.out.println(categoryMapper.findById(1));

		// @Spy 真实调用
		list.add("小曹哇小曹～");
		log.error(list.toString());

		// @SptBean 真实调用。
		// 区别：@Spy调用真实方法时，bean是无法注入的。@SpyBean则可以注入
		log.error(skuMapper.findById(1).toString());
	}
}
