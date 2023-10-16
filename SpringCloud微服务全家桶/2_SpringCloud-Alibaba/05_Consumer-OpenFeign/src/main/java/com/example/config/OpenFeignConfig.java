package com.example.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-16 22:07
 * @apiNote TODO
 */
public class OpenFeignConfig {

    // 设置负载均衡策略
    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorLoadBalancer(Environment e, LoadBalancerClientFactory factory) {
        // 获取负载均衡客户端名称，即提供者服务名称
        String name = e.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        // 从所有 provider 实例中指定名称的实例列表中随机选择一个实例
        // 参数 1：获取指定名称的所有 provider 实例列表
        // 参数 2：指定要获取的 provider 服务名称
        return new RandomLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
