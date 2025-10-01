package com.example;

import com.example.application.PositionDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

// 	1、Mockito的API
// 	   mock：       构建一个我们需要的对象；可以mock具体的对象，也可以mock接口
// 	   spy：        构建监控对象
// 	   verify：     验证某种行为
// 	   when：       当执行什么操作的时候，一般配合thenXXX 一起使用。表示执行了一个操作之后产生什么效果
// 	   doReturn：   返回什么结果
// 	   doThrow：    抛出一个指定异常
// 	   doAnswer：   做一个什么相应，需要我们自定义Answer
// 	   times：      某个操作执行了多少次
// 	   atLeastOnce：某个操作至少执行一次
// 	   atLeast：    某个操作至少执行指定次数
// 	   atMost：     某个操作至多执行指定次数
// 	   atMostOnce： 某个操作至多执行一次
// 	   doNothing：  不做任何处理
// 	   doReturn：   返回一个结果
// 	   doThrow：    抛出一个指定异常
// 	   doAnswer：   指定一个操作，传入Answer
// 	   doCallRealMethod：返回真实业务执行的结果，只能用于监控对象
// 2、ArgumentMatchers参数匹配
// 	   anyInt：     任何int类型的参数，类似的还有 anyLong/anyByte/anyList 等等。
// 	   eq：         等于某个值的时候，如果是对象类型的，则看toString()方法
// 	   isA：        匹配某种类型
// 	   matches：    使用正则表达式进行匹配
// 3、OngoingStubbing返回操作
// 	   thenReturn： 指定一个返回的值
// 	   thenThrow：  抛出一个指定异常
// 	   then：       指定一个操作，需要传入自定义Answer
// 	   thenCallRealMethod：返回真实业务执行的结果，只能用于监控对象
@ExtendWith(MockitoExtension.class)
// 这里我们只是做单元测试，无需Spring 的上下文环境，因此无需添加 @SpringBootTest 注解
// 可以通过 @ExtendWith 注解，启用 Mockito 的扩展功能：如 @Mock、@Spy、@InjectMocks 注解的支持
public class SpringBootMockitoApplicationTests {

	// 被 @BeforeEach 标注的方法会在每个测试方法执行前被调用，执行一些初始化操作，如初始化变量、设置mock对象等
	@BeforeEach
	public void init() {
		// MockitoAnnotations.openMocks(this);
		// 上述方法会扫描当前测试类中的所有 @Mock、@Spy、@InjectMock 注解，并为它们创建相应的模拟对象。
		// 目前最新的 SpringBoot Test 版本中，已经不需要手动调用该方法了。建议使用 @ExtendWith(MockitoExtension.class)
		System.out.println("初始化方法");
	}

	// 被 @AfterEach 标注的方法会在每个测试方法执行后被调用
	@AfterEach
	public void tearDown() {
		System.out.println("销毁方法");
	}

	@Test
	public void test1() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto = Mockito.mock(PositionDto.class);
		PositionDto spyPositionDto = Mockito.spy(PositionDto.class);

		// TODO Mockito.when().thenReturn() 会先执行真实方法，然后覆盖返回值。
		// 打桩1：定义当调用 mock positionService 的 getStr() 方法，并且参数是字符串 "美女" 时，就返回字符串 "刘亦菲"
		Mockito.when(mockPositionDto.getStr("美女")).thenReturn("刘亦菲");
		System.out.println("when...thenReturn语法：" + mockPositionDto.getStr("美女"));

		// 打桩2：定义当调用mock positionService 的 getStr() 方法，并且任意字符串参数时，就返回字符串 "hello"
		Mockito.when(mockPositionDto.getStr(Mockito.anyString())).thenReturn("hello");
		System.out.println("when...thenReturn语法：" + mockPositionDto.getStr(Mockito.anyString()));

		// TODO Mockito.doReturn().when() 是直接返回值，不会执行真实方法。
		// 打桩3：定义当调用mock positionService 的 getStr() 方法，并且任意字符串参数时，就返回字符串 "world"
		Mockito.doReturn("world").when(mockPositionDto).getStr("靓仔");
		System.out.println("doReturn...when语法：" + mockPositionDto.getStr(Mockito.anyString()));

		// 打桩4：定义当调用mock positionService 的 getStr() 方法，并且任意字符串参数时，就返回字符串 "world"
		Mockito.doReturn("world").when(spyPositionDto).getStr(Mockito.anyString());
		System.out.println("doReturn...when语法：" + mockPositionDto.getStr(Mockito.anyString()));

		// 验证 positionService 的 getStr()这个方法是否被调用过
		Mockito.verify(mockPositionDto).getStr("美女");

		// 检查调用 positionService 的 getStr() 方法，且参数为 “仙女儿” 的次数是否为 2次
		Mockito.verify(mockPositionDto, Mockito.times(2)).getStr(Mockito.eq("仙女儿"));
	}

	@Test
	public void test2() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto = Mockito.mock(PositionDto.class);

		// TODO Mockito.when().thenThrow() 会先执行真实方法，然后覆盖抛出的异常。
		// 打桩1：当调用 mock positionService 的 getStr() 方法，输入的的参数是 字符串 “9” 时，抛出一个 RuntimeException
		Mockito.when(mockPositionDto.getStr("9"))
		       .thenThrow(new RuntimeException("mock throw exception"));
		Assertions.assertThrows(RuntimeException.class, () -> {
			mockPositionDto.getStr("9");
		});

		// TODO Mockito.doReturn().when() 是直接抛出的异常，不会执行真实方法。
		// 打桩2：当调用 mock positionService 的 getStr() 方法，输入的的参数是 字符串 “9” 时，抛出一个 RuntimeException
		Mockito.doThrow(new RuntimeException("mock throw exception"))
		       .when(mockPositionDto).getStr("9");
		Assertions.assertThrows(RuntimeException.class, () -> {
			mockPositionDto.getStr("9");
		});

		// TODO Mockito.doNothing().when() 适用于没有返回值的方法。
		// 打桩：如果方法没有返回值的话（即方法为 public void myMethod() {…}）,要使用 doNothing() 来打桩
		Mockito.doNothing().when(mockPositionDto).getVoid();
		mockPositionDto.getVoid();
	}

	@Test
	public void test3() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto = Mockito.mock(PositionDto.class);

		// 验证调用顺序，验证 positionService 是否先调用 getStr() 两次，并且第一次的参数是 “3”、第二次的参数是 “5”，然后才调用 getVoid() 方法
		InOrder inOrder = Mockito.inOrder(mockPositionDto);
		inOrder.verify(mockPositionDto).getStr("3");
		inOrder.verify(mockPositionDto).getStr("5");
		inOrder.verify(mockPositionDto).getVoid();
	}

	@Test
	public void test4() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto = Mockito.mock(PositionDto.class);

		// TODO 多次调用返回不同值的两种方式
		// 方式1：when().thenReturn() 链式调用。
		Mockito.when(mockPositionDto.getStr("1"))
				.thenReturn("A")
				.thenReturn("B")
				.thenReturn("C");

		// 方式2：doReturn().when() 多次设置。
		Mockito.doReturn("A")
				.doReturn("B")
				.doReturn("C")
				.when(mockPositionDto).getStr("2");
	}
}
