package com.example.init.rule;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.example.service.impl.EntryServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 18:24
 * @apiNote TODO 定义熔断规则
 */
@Component
public class CustomDegrade implements CommandLineRunner {

    // TODO 熔断规则:
    //  在统计时长1000ms里，请求数达到或者超过 最小请求数2时，其中有 2*0.2=0.4 以上请求数的最大请求时间不少于200ms,
    //  那么就会触发 Sentinel的熔断机制，并且熔断时长在10s内，任何请求都无法访问该资源。
    @Override
    public void run(String... args) throws Exception {
        ArrayList<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        // 指定将当前规则应用于Sentinel的资源名称
        rule.setResource(EntryServiceImpl.GET_LIST);
        // 指定熔断策略为慢调用比例（慢调用比例、异常比例、异常数）
        // 慢调用比例：超时(慢)请求所占比例作为闸门
        // 异常比例：异常请求所占比例作为闸门
        // 异常数：异常请求数量作为闸门
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 指定最大RT（最大的超时时间）
        rule.setCount(1);
        // 指定比例阈值
        rule.setSlowRatioThreshold(0.2);
        // 指定熔断时长（在熔断时间内任何请求都无法访问该资源）
        rule.setTimeWindow(10);
        // 指定最小请求数（至少有三个以上的请求才可能触发熔断）
        rule.setMinRequestAmount(3);
        // 指定统计时长
        rule.setStatIntervalMs(1000);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
