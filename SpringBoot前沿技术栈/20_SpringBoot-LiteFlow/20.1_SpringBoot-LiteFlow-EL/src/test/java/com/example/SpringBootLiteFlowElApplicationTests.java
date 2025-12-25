package com.example;

import com.yomahub.liteflow.core.FlowExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLiteFlowElApplicationTests {

	// 注入liteflow的执行器
	@Autowired
	private FlowExecutor liteFlowExecutor;

	@Test
	void contextLoads() {
		// 串行编排 - THEN
		liteFlowExecutor.execute2Resp("chain1");
		// 并行编排 - WHEN
		liteFlowExecutor.execute2Resp("chain2_1");
		liteFlowExecutor.execute2Resp("chain2_2");
		// 条件编排 - IF
		liteFlowExecutor.execute2Resp("chain3_1");
		liteFlowExecutor.execute2Resp("chain3_2");
		// 选择编排 - Switch
		liteFlowExecutor.execute2Resp("chain4");
	}

	@Test
	void contextLoads2() {
		// 循环编排 - FOR
		liteFlowExecutor.execute2Resp("chain5_1");
		// 循环编排 - WHILE
		liteFlowExecutor.execute2Resp("chain5_2");
		// 循环编排 - ITERATOR(迭代器)
		liteFlowExecutor.execute2Resp("chain5_3");
	}
}
