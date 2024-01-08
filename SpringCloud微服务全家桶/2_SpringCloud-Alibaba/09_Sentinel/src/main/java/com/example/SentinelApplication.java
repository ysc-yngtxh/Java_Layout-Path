package com.example;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.service.impl.SentinelMethodServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelApplication.class, args);
		initFlowQpsRule();
	}

	// 定义了每秒最多接收2个请求
	private static void initFlowQpsRule() {
		List<FlowRule> rules = new ArrayList<>();
		// FlowRule流量控制规则
		FlowRule rule = new FlowRule(SentinelMethodServiceImpl.RESOURCE_NAME);
		// set limit qps to 2  限流阈值
		rule.setCount(2);
		// 限流阈值类型，QPS 模式（1）或并发线程数模式（0）
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// 流控针对的调用来源，若为 default 则不区分调用来源
		rule.setLimitApp("default");
		// 调用关系限流策略：直接、链路、关联
		// rule.setStrategy(0);
		// 流量控制效果(直接拒绝、Warm Up、匀速排队)
		// rule.setControlBehavior(0);
		// 是否集群限流
		// rule.setClusterMode(false);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}
}