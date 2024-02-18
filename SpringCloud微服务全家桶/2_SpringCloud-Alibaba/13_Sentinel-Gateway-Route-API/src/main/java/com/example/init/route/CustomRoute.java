package com.example.init.route;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 18:24
 * @apiNote TODO 定义Route路由规则
 */
@Component
public class CustomRoute implements CommandLineRunner {

    // TODO 定义Route路由规则
    @Override
    public void run(String... args) throws Exception {
        Set<GatewayFlowRule> rules = new HashSet<>();
        GatewayFlowRule rule = new GatewayFlowRule();

        // 指定资源模式为route，默认值为route
        rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_ROUTE_ID);
        // 指定要限流的route的id
        rule.setResource("my_route-1");
        // 指定限流规则为QPS流控，默认值为QPS流控
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS阈值
        rule.setCount(3);
        // 指定统计时长，默认值为1秒
        rule.setIntervalSec(1);
        // 指定流控方式为“快速失败”，默认值为“快速失败”
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        // 指定流控方式为“匀速排队”
        // rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        // 指定排队等待超时时长，默认值为500秒
        // rule.setMaxQueueingTimeoutMs(500);

        // 针对请求属性设置
        // GatewayParamFlowItem item = new GatewayParamFlowItem();
        // 指定针对的“参数属性”类型为“URL参数”
        // item.setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM);
        // 指定“URL参数名称”
        // item.setFieldName("name");
        // 指定匹配模式为“子串”
        // item.setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_CONTAINS);
        // 正则匹配
        // item.setPattern("ang");

        // 针对请求属性设置
        // rule.setParamItem(item);

        rules.add(rule);
        GatewayRuleManager.loadRules(rules);
    }
}
