package com.example.init.rule;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.example.service.impl.SentinelServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 18:30
 * @apiNote TODO 定义热点规则
 */
@Component
public class CustomParam implements CommandLineRunner {

    // TODO 定义热点规则
    @Override
    public void run(String... args) throws Exception {
        List<ParamFlowRule> rules = new ArrayList<>();
        // FlowRule流量控制规则
        ParamFlowRule rule = new ParamFlowRule(SentinelServiceImpl.GET_LIST);
        // 必须指定限流规则类型，QPS 模式（热点规则只有该模式）
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 限流阈值
        rule.setCount(3);
        // 指定参数索引为1的参数进行流控
        rule.setParamIdx(1);
        // 指定限流的时长为10秒，10秒后还可在阈值范围内访问
        rule.setDurationInSec(10);
        // 参数例外项
        List<ParamFlowItem> items = new ArrayList<>();
        items.add(nameParamItem("admin", 1));
        items.add(nameParamItem("text", 100));
        items.add(nameParamItem("zs", 200));
        rule.setParamFlowItemList(items);
        // 是否集群限流
        // rule.setClusterMode(false);
        rules.add(rule);
        ParamFlowRuleManager.loadRules(rules);
    }

    private ParamFlowItem nameParamItem(String name, int count) {
        ParamFlowItem item = new ParamFlowItem();
        // 参数例外项 -- 参数类型
        item.setClassType(String.class.getTypeName());
        // 参数例外项 -- 参数值
        item.setObject(name);
        // 参数例外项 -- 特殊QPS阈值
        item.setCount(count);
        return item;
    }
}
