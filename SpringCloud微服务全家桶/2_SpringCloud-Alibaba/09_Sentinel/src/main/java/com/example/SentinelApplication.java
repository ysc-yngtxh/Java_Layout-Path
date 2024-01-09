package com.example;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.service.impl.SentinelClassServiceImpl;
import com.example.service.impl.SentinelMethodServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelApplication.class, args);
		initDegradeRule();
		initFlowQpsRule();
	}

	// TODO 定义熔断规则:
	//  在统计时长1000ms里，请求数达到或者超过 最小请求数2时，其中有 2*0.2=0.4 以上请求数的最大请求时间不少于200ms,
	//  那么就会触发 Sentinel的熔断机制，并且熔断时长在10s内，任何请求都无法访问该资源。
	private static void initDegradeRule() {
		ArrayList<DegradeRule> rules = new ArrayList<>();
		DegradeRule rule = new DegradeRule();
		// 指定将当前规则应用于Sentinel的资源名称
		rule.setResource(SentinelMethodServiceImpl.RESOURCE_METHOD);
		// 指定熔断策略为慢调用比例（慢调用比例、异常比例、异常数）
		// 慢调用比例：超时(慢)请求所占比例作为闸门
		// 异常比例：异常请求所占比例作为闸门
		// 异常数：异常请求数量作为闸门
		rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
		// 指定最大RT（最大的超时时间）
		rule.setCount(200);
		// 指定比例阈值
		rule.setSlowRatioThreshold(0.2);
		// 指定熔断时长（在熔断时间内任何请求都无法访问该资源）
		rule.setTimeWindow(10);
		// 指定最小请求数（至少有两个以上的请求才可能触发熔断）
		rule.setMinRequestAmount(2);
		// 指定统计时长
		rule.setStatIntervalMs(1000);
		rules.add(rule);
		DegradeRuleManager.loadRules(rules);
	}


	// 定义流控规则，每秒最多接收2个请求
	private static void initFlowQpsRule() {
		List<FlowRule> rules = new ArrayList<>();
		// FlowRule流量控制规则
		FlowRule rule = new FlowRule(SentinelMethodServiceImpl.RESOURCE_METHOD);
		FlowRule rule1 = new FlowRule(SentinelClassServiceImpl.RESOURCE_CLASS);
		// set limit qps to 2  限流阈值
		rule.setCount(2);
		rule1.setCount(2);
		// 限流阈值类型，QPS 模式（1）或并发线程数模式（0）
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// 流控针对的调用来源，若为 default 则不区分调用来源
		rule.setLimitApp("default");
		rule1.setLimitApp("default");
		// 调用关系限流策略：直接、链路、关联
		// rule.setStrategy(0);
		// 流量控制效果(直接拒绝、Warm Up、匀速排队)
		// rule.setControlBehavior(0);
		// 是否集群限流
		// rule.setClusterMode(false);
		rules.add(rule);
		rules.add(rule1);
		FlowRuleManager.loadRules(rules);
	}
}