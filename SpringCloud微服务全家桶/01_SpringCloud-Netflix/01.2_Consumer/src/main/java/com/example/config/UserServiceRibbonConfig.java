package com.example.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.PollingServerListUpdater;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerListFilter;
import com.netflix.loadbalancer.ServerListUpdater;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;

public class UserServiceRibbonConfig {
    
    /**
     * 配置负载均衡规则
     */
    @Bean
    public IRule ribbonRule() {
        // 可选规则：
        // return new RoundRobinRule();           // 轮询（默认）
        // return new WeightedResponseTimeRule(); // 加权
        // return new BestAvailableRule();        // 选择最小并发请求
        // return new ZoneAvoidanceRule();        // 区域感知
        return new RandomRule();  // 返回随机规则（替代默认的轮询）
    }
    
    /**
     * 配置Ping机制（检查服务实例是否存活）
     */
    @Bean
    public IPing ribbonPing() {
        // 使用默认的DummyPing（不实际检查）
        // return new DummyPing();
        return new PingUrl();  // 使用实际ping检查
    }
    
    /**
     * 配置服务列表更新策略
     */
    @Bean
    public ServerListUpdater ribbonServerListUpdater() {
        return new PollingServerListUpdater();
    }
    
    /**
     * 配置服务列表过滤器
     */
    @Bean
    public ServerListFilter<Server> ribbonServerListFilter() {
        ZonePreferenceServerListFilter filter = new ZonePreferenceServerListFilter();
        filter.setZone("myZone");
        return filter;
    }
}
