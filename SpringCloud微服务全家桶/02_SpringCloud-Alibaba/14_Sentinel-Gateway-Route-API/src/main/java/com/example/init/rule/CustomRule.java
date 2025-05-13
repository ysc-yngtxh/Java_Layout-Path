package com.example.init.rule;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 18:30
 * @apiNote TODO 定义API与Route规则
 */
@Component
public class CustomRule implements CommandLineRunner {

    // TODO Sentinel 网关流控默认的粒度是 route 维度以及自定义 API 分组维度，默认不支持 URL 粒度。
    //      若通过 Spring Cloud Alibaba 接入，请将 spring.cloud.sentinel.filter.enabled 配置项置为 false
    //      若在网关流控控制台上看到了 URL 资源，就是此配置项没有置为 false。

    // TODO route 维度：即对yml文件中Gateway网关设置的路由规则所满足的请求进行限流
    //      API 分组维度：即通过对网关访问其他服务的API接口进行分组，并将满足这些分组的请求进行限流
    @Override
    public void run(String... args) throws Exception {
        Set<GatewayFlowRule> rules = new HashSet<>();

        // 设置API分组；当然Route路由规则规则不需要
        apiGroup();

        // 设置Route限流规则：即对yml文件中Gateway网关设置的路由规则所满足的请求进行限流
        GatewayFlowRule routeRule = GeneralRules("my_route-1");
        // 设置API限流规则：即通过对网关访问其他服务的API接口进行分组，并将满足这些分组的请求进行限流
        GatewayFlowRule apiRule = GeneralRules("some_customized_api");

        // ⚠️：需要注意的是，尽量将各个规则放入同一个Set中。
        //     我之前有点想当然了，创建了两个规则类，执行了两遍 GatewayRuleManager.loadRules(rules);
        //     结果就是其中的一个规则类覆盖了另一个规则类，导致最终的流控规则只有一个生效。

        rules.add(apiRule);
        rules.add(routeRule);
        GatewayRuleManager.loadRules(rules);
    }

    // 配置API分组
    private void apiGroup() {
        Set<ApiDefinition> definitions = new HashSet<>();

        // 定义一个"some_customized_api"的API分组
        ApiDefinition api1 = new ApiDefinition("some_customized_api").setPredicateItems(
                new HashSet<ApiPredicateItem>() {{
                    // 双括号初始化实际上是创建了HashSet的匿名子类，并在该子类的实例化代码块中进行了元素添加。
                    // 这种写法相比普通集合add()方法会有额外的内存开销，因为它实际上创建了一个匿名子类。
                    // 此外，它还可能与一些静态分析工具产生冲突，因为它需要对子类进行解析。还是推荐普通add().
                    add(new ApiPathPredicateItem().setPattern("/provider/time"));
                    // 设置 URL 精确匹配策略
                    add(new ApiPathPredicateItem().setPattern("/header").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_EXACT));
                }});

        // 定义一个"another_customized_api"的API分组
        ApiDefinition api2 = new ApiDefinition("another_customized_api").setPredicateItems(
                new HashSet<ApiPredicateItem>() {{
                    // 设置 URL 匹配策略前缀
                    add(new ApiPathPredicateItem().setPattern("/ahas/**").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});

        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }


    // TODO API规则与Route写法差不多。也就两处设置要区分开来：
    //      1、Mode模式设置（API值为1，Route值为0）  2、限流Id（API指定分组Id，Route指定路由Id）
    private GatewayFlowRule GeneralRules(String id) {
        GatewayFlowRule rule = new GatewayFlowRule();
        // 指定资源模式为API，默认值为route
        rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME);
        // 指定要限流的API分组的id
        rule.setResource(id);
        // 指定限流规则为QPS流控，默认值为QPS流控
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS阈值
        rule.setCount(2);
        // 指定统计时长，默认值为1秒
        rule.setIntervalSec(1);
        // 指定流控方式为“快速失败”，默认值为“快速失败”
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        // 指定流控方式为“匀速排队”
        // rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        // 指定排队等待超时时长，默认值为500秒
        // rule.setMaxQueueingTimeoutMs(500);

        // 针对请求属性设置
        // GatewayParamFlowItem item = setAttributes();
        // rule.setParamItem(item);
        return rule;
    }

    // 网关流控中针对请求属性设置
    private GatewayParamFlowItem setAttributes() {
        // 针对请求属性设置
        GatewayParamFlowItem item = new GatewayParamFlowItem();
        // 指定针对的“参数属性”类型为“URL参数”
        item.setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM);
        // 指定“URL参数名称”
        item.setFieldName("name");
        // 指定匹配模式为“子串”
        item.setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_CONTAINS);
        // 正则匹配
        item.setPattern("ang");
        return item;
    }
}
