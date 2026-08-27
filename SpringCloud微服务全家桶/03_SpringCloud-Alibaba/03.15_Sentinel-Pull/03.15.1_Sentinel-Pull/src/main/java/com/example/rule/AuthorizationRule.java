package com.example.rule;

import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 22:00
 * @apiNote TODO 动态授权规则
 */
public class AuthorizationRule {

    public void readWriteRuleFileFlow(String ruleFilePath) throws IOException {
        String ruleFile = ruleFilePath + "/authority-rule.csv";
        createRuleFile(ruleFile);

        // FileRefreshableDataSource定时从指定文件中读取规则JSON文件，如果发现文件发生变化，就更新规则缓存
        ReadableDataSource<String, List<AuthorityRule>> ds = new FileRefreshableDataSource<>(
                ruleFile, source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {})
        );
        // 将可读数据源注册至 AuthorityRuleManager.
        AuthorityRuleManager.register2Property(ds.getProperty());

        // FileWritableDataSource 接收控制台规则推送，并根据配置，修改规则JSON文件
        WritableDataSource<List<AuthorityRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerAuthorityDataSource(wds);
    }

    private void createRuleFile(String ruleFile) throws IOException {
        File file = new File(ruleFile);
        // 创建文件
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private <T> String encodeJson(T t) {
        // 将对象t转换为Json字符串
        return JSON.toJSONString(t);
    }
}
