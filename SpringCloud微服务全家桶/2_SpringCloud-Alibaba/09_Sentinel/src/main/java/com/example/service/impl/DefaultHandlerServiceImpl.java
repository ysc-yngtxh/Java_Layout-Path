package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.service.DefaultHandlerService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 16:07
 * @apiNote TODO
 */
@Service
public class DefaultHandlerServiceImpl implements DefaultHandlerService {

    public static final String DEFINITION_BLOCK_RULE ="definitionBlockRule";

    public String defaultBlockRule() {
        return "此时访问成功未被限流，且该资源限流为Sentinel默认限流处理逻辑！！！";
    }


    // TODO @SentinelResource注解用于定义资源。加上该注解，Sentinel 才会对该资源进行流量监控。
    //      该注解提供可选的异常处理和 fallback 配置项，尽量应用于业务层(service)，属于资源名称限流。
    //  @SentinelResource：value属性是定义的资源名称，在Sentinel仪表盘的簇点链路显示，也用于代码逻辑对流控、熔断规则时资源的指定。
    //                     blockHandler属性是针对流控设置，超出规则，则会进入blockHandler方法。
    //                     fallback属性是针对方法出现异常了，则会进入fallback方法。
    //  若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出。
    @SentinelResource(value = DEFINITION_BLOCK_RULE)
    public String definitionBlockRule() {
        return "定义了 definitionBlockRule 在业务层的资源名！！！";
    }
}
