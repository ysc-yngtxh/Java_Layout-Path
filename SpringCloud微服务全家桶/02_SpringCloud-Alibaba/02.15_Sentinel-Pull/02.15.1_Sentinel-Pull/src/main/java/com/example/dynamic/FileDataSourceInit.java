package com.example.dynamic;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.example.rule.AuthorizationRule;
import com.example.rule.FallbackRule;
import com.example.rule.FlowRule;
import com.example.rule.ParamRule;
import com.example.rule.SystemerRule;

import java.io.File;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-18 22:30
 * @apiNote TODO
 */
public class FileDataSourceInit implements InitFunc {

    @Override
    public void init() throws Exception {
        // 获取该子模块项目路径
        String moduleTarget = FileDataSourceInit.class.getClassLoader().getResource("").toURI().getPath();
        String module = moduleTarget.substring(0, moduleTarget.indexOf("/target"));
        // 在当前模块根目录下创建/rules目录
        File ruleFileDir = new File(module + "/rules");
        // 创建目录
        if (!ruleFileDir.exists()) {
            ruleFileDir.mkdirs();
        }

        // 动态读写流控规则
        FlowRule flowRule = new FlowRule();
        flowRule.readWriteRuleFileFlow(ruleFileDir.getPath());
        // 动态读写熔断规则
        FallbackRule fallbackRule = new FallbackRule();
        fallbackRule.readWriteRuleFileFlow(ruleFileDir.getPath());
        // 动态读写热点规则
        ParamRule paramRule = new ParamRule();
        paramRule.readWriteRuleFileFlow(ruleFileDir.getPath());
        // 动态读写授权规则
        AuthorizationRule authorizationRule = new AuthorizationRule();
        authorizationRule.readWriteRuleFileFlow(ruleFileDir.getPath());
        // 动态读写系统规则
        SystemerRule systemerRule = new SystemerRule();
        systemerRule.readWriteRuleFileFlow(ruleFileDir.getPath());
    }
}
