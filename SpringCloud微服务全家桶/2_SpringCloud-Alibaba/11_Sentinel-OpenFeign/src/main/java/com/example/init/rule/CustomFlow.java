package com.example.init.rule;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 18:28
 * @apiNote TODO 定义流控规则
 */
@Component
public class CustomFlow implements CommandLineRunner {

    // TODO 定义流控规则，每秒最多接收2个请求
    @Override
    public void run(String... args) throws Exception {
        List<FlowRule> rules = new ArrayList<>();
        // FlowRule流量控制规则
        FlowRule rule = new FlowRule("/customException"); // URL资源限流
        // 限流阈值
        rule.setCount(1);
        // 限流阈值类型，QPS 模式（1）或并发线程数模式（0）
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 流控针对的调用来源，若为 default 则不区分调用来源
        rule.setLimitApp("default");
        // 调用关系限流策略：直接、链路、关联
        // rule.setStrategy(RuleConstant.STRATEGY_DIRECT);
        // 流量控制效果(直接拒绝、Warm Up、匀速排队)
        // rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        // 是否集群限流
        // rule.setClusterMode(false);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
