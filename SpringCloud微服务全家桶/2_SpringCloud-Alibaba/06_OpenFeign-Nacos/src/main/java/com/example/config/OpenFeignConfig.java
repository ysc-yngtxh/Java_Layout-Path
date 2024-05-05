package com.example.config;

import feign.Logger;
import feign.Retryer;
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

    // 设置LoadBalancer负载均衡策略
    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorLoadBalancer(Environment e, LoadBalancerClientFactory factory) {
        // 获取负载均衡客户端名称，即提供者服务名称
        String name = e.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        // 从所有 provider 实例中指定名称的实例列表中随机选择一个实例
        // 参数 1：获取指定名称的所有 provider 实例列表
        // 参数 2：指定要获取的 provider 服务名称
        return new RandomLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }

    // 设置OpenFeign的重试
    @Bean
    public Retryer retryer() {
        // 初始间隔时间为100ms，重试最大时间间隔为1s，最大重试次数为3次（1次正常请求+2次重试请求）
        return new Retryer.Default(100, 1, 3);
    }

    // 当OpenFeign进行重试，我们发现日志只有一条信息。所以要想查看重试的每一条日志信息，就必须将OpenFeign的日志级别修改为最高
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
