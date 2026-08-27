package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.handler.BlockClassHandler;
import com.example.handler.FallbackClassHandler;
import com.example.service.SentinelClassService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-08 12:50
 * @apiNote TODO
 */
@Service
public class SentinelClassServiceImpl implements SentinelClassService {

    public static final String RESOURCE_CLASS = "selectClassById";

    // TODO @SentinelResource注解用于定义资源。加上该注解，Sentinel 才会对该资源进行流量监控。
    //      该注解提供可选的异常处理和 fallback 配置项，尽量应用于业务层(service)，属于资源名称限流。
    //  @SentinelResource：value属性是定义的资源名称，在Sentinel仪表盘的簇点链路显示，也用于代码逻辑对流控、熔断规则时资源的指定。
    //                     blockHandler属性是针对流控设置，超出规则，则会进入blockHandler方法。
    //                     fallback属性是针对方法出现异常了，则会进入fallback方法。
    //  若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出BlockException时只会进入 blockHandler 处理逻辑。
    //  若只配置 value属性，未配置 blockHandler、fallback等属性，当访问该资源被限流时，直接报错500。
    //  注意：注解方式埋点不支持 private 方法。所以注解里属性对应的类方法一定不能是私有方法的。
    @SentinelResource(blockHandlerClass = BlockClassHandler.class, blockHandler = "selectClassByIdBlockHandler",
                      fallbackClass = FallbackClassHandler.class, fallback = "selectClassByIdFallbackHandler")
    public String selectClassById(Integer id) {
        if (id == 2) {
            throw new RuntimeException("出异常了啊！");
        }
        return "{id: " + id + ", age: 25}";
    }
}
