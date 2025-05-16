package com.example;

import com.example.application.PositionDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

// 	1、Mockito的API
// 	   mock：构建一个我们需要的对象；可以mock具体的对象，也可以mock接口
// 	   spy：构建监控对象
// 	   verify：验证某种行为
// 	   when：当执行什么操作的时候，一般配合thenXXX 一起使用。表示执行了一个操作之后产生什么效果
// 	   doReturn：返回什么结果
// 	   doThrow：抛出一个指定异常
// 	   doAnswer：做一个什么相应，需要我们自定义Answer
// 	   times：某个操作执行了多少次
// 	   atLeastOnce：某个操作至少执行一次
// 	   atLeast：某个操作至少执行指定次数
// 	   atMost：某个操作至多执行指定次数
// 	   atMostOnce：某个操作至多执行一次
// 	   doNothing：不做任何处理
// 	   doReturn：返回一个结果
// 	   doThrow：抛出一个指定异常
// 	   doAnswer：指定一个操作，传入Answer
// 	   doCallRealMethod：返回真实业务执行的结果，只能用于监控对象
// 2、ArgumentMatchers参数匹配
// 	   anyInt：任何int类型的参数，类似的还有anyLong/anyByte等等。
// 	   eq：等于某个值的时候，如果是对象类型的，则看toString方法
// 	   isA：匹配某种类型
// 	   matches：使用正则表达式进行匹配
// 3、OngoingStubbing返回操作
// 	   thenReturn：指定一个返回的值
// 	   thenThrow：抛出一个指定异常
// 	   then：指定一个操作，需要传入自定义Answer
// 	   thenCallRealMethod：返回真实业务执行的结果，只能用于监控对象
@SpringBootTest
public class SpringBootMockitoApplicationTests {

	// 被 @BeforeEach 标注的方法会在每个测试方法执行前被调用
	@BeforeEach
	public void init() {
		// 执行一些初始化操作，如初始化变量、设置mock对象等
		System.out.println("初始化方法");
	}

	// 被 @AfterEach 标注的方法会在每个测试方法执行后被调用
	@AfterEach
	public void after() {
		System.out.println("销毁方法");
	}

	@Test
	public void test1() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto1 = Mockito.mock(PositionDto.class);

		// 打桩：定义当调用mock positionService 的 getStr() 方法，并且任意字符串参数时，就返回字符串 "hello"
		Mockito.when(mockPositionDto1.getStr(Mockito.anyString())).thenReturn("hello");
		System.out.println(mockPositionDto1.getStr(Mockito.anyString()));

		// 打桩：定义当调用 mock positionService 的 getStr() 方法，并且参数是字符串 "美女" 时，就返回字符串 "刘亦菲"
		Mockito.when(mockPositionDto1.getStr("美女")).thenReturn("刘亦菲");
		System.out.println(mockPositionDto1.getStr("美女"));

		// 验证 positionService 的 getStr()这个方法是否被调用过
		Mockito.verify(mockPositionDto1).getStr("美女");

		// 检查调用 positionService 的 getStr() 方法，且参数为 “3” 的次数是否为1次
		Mockito.verify(mockPositionDto1, Mockito.times(1)).getStr(Mockito.eq("3"));
	}

	@Test
	public void test2() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto2 = Mockito.mock(PositionDto.class);

		// 打桩：当调用 mock positionService 的 getStr() 方法，输入的的参数是 字符串 “9” 时，抛出一个 RuntimeException
		Mockito.when(mockPositionDto2.getStr("9")).thenThrow(new RuntimeException("mock throw exception"));
		// 会抛出一个RuntimeException
		String str = mockPositionDto2.getStr("9");
	}

	@Test
	public void test3() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto3 = Mockito.mock(PositionDto.class);

		// 打桩：如果方法没有返回值的话（即是方法定义为 public void myMethod() {…}），要改用 doThrow() 抛出 Exception
		Mockito.doThrow(new RuntimeException("mock throw exception when method is void"))
		       .when(mockPositionDto3).getVoid();
		// 会抛出一个RuntimeException
		mockPositionDto3.getVoid();
	}

	@Test
	public void test4() {
		// TODO 手动配置单个模拟对象
		PositionDto mockPositionDto4 = Mockito.mock(PositionDto.class);

		// 验证调用顺序，验证 positionService 是否先调用 getStr() 两次，并且第一次的参数是 “3”、第二次的参数是 “5”，然后才调用 getVoid() 方法
		InOrder inOrder = Mockito.inOrder(mockPositionDto4);
		inOrder.verify(mockPositionDto4).getStr("3");
		inOrder.verify(mockPositionDto4).getStr("5");
		inOrder.verify(mockPositionDto4).getVoid();
	}
}
