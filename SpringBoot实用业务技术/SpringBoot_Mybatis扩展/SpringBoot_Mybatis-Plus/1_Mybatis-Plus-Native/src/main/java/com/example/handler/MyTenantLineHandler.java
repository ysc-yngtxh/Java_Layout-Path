package com.example.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public class MyTenantLineHandler implements TenantLineHandler {

    private final List<String> IGNORE_TENANT_TABLE = Arrays.asList("tb_consumer", "tb_sku", "tb_order");

    /**
     * 获取租户ID 实际应该从用户信息中获取
     */
    @Override
    public Expression getTenantId() {
        // 正常项目从header的token中获取租户信息，采用 自定义上下文组件传递租户id
        // 这里我们模拟获取 tenant_id
        log.info("==========================getTenantId");
        String userTenantId = "000" + (new Random().nextInt(2) + 1);
        return new StringValue(userTenantId);
    }

    /**
     * 获取租户表字段 默认为tenant_id
     */
    @Override
    public String getTenantIdColumn() {
        log.info("==========================getTenantIdColumn");
        return "tenant_id";
    }

    /**
     * 表过滤，返回true，表示当前表不进行租户过滤
     * @param tableName 表名
     * @return
     */
    @Override
    public boolean ignoreTable(String tableName) {
        // 排除表
        log.info("==========================ignoreTable By TenantId");
        return IGNORE_TENANT_TABLE.contains(tableName);
    }
}
