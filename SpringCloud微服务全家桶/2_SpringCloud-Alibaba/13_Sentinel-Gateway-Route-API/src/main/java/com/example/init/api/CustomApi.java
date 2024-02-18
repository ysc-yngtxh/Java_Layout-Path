package com.example.init.api;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 18:28
 * @apiNote TODO 定义API规则
 */
@Component
public class CustomApi implements CommandLineRunner {

    // TODO 定义API分组规则：API与Route方式最大的不同就在于API分组
    private void extracted() {
        Set<ApiDefinition> definitions = new HashSet<>();

        // 定义一个"some_customized_api"的API分组
        ApiDefinition api1 = new ApiDefinition("some_customized_api").setPredicateItems(
                new HashSet<ApiPredicateItem>() {{
                    // 双括号初始化实际上是创建了HashSet的匿名子类，并在该子类的实例化代码块中进行了元素添加。
                    // 这种写法相比普通集合add()方法会有额外的内存开销，因为它实际上创建了一个匿名子类。
                    // 此外，它还可能与一些静态分析工具产生冲突，因为它需要对子类进行解析。还是推荐普通add().
                    add(new ApiPathPredicateItem().setPattern("/product/baz"));
                    add(new ApiPathPredicateItem().setPattern("/product/foo/**").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});

        // 定义一个"another_customized_api"的API分组
        ApiDefinition api2 = new ApiDefinition("another_customized_api").setPredicateItems(
                new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/ahas"));
                }});

        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    // TODO API规则与Route写法差不多。也就两处设置要区分开来：
    //      1、Mode模式设置（API值为1，Route值为0）  2、限流Id（API指定分组Id，Route指定路由Id）
    @Override
    public void run(String... args) throws Exception {
        Set<GatewayFlowRule> rules = new HashSet<>();
        GatewayFlowRule rule = new GatewayFlowRule();

        // 指定资源模式为API，默认值为route
        rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME);
        // 指定要限流的API的id
        rule.setResource("some_customized_api");
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
