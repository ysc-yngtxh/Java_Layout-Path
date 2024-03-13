package com.example.init.rule;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.example.service.impl.SentinelServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 18:24
 * @apiNote TODO 定义授权规则
 */
@Component
public class CustomAuthority implements CommandLineRunner {

    // TODO 授权规则: 定义流控来源为sa,sb,sc的请求为白名单，其余的则为黑名单
    @Override
    public void run(String... args) throws Exception {
        ArrayList<AuthorityRule> rules = new ArrayList<>();
        AuthorityRule rule = new AuthorityRule();
        // 指定将当前规则应用于Sentinel的资源名称
        rule.setResource(SentinelServiceImpl.GET_HANDLER);
        // 流控针对的调用来源，若为 default 则不区分调用来源;
        rule.setLimitApp("sa,sb,sc");
        // 设置授权规则中的白名单(RuleConstant.AUTHORITY_WHITE)或者黑名单(RuleConstant.AUTHORITY_BLACK)
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        rules.add(rule);
        AuthorityRuleManager.loadRules(rules);
    }
}
