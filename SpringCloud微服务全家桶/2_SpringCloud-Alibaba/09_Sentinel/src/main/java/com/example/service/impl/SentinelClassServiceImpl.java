package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.handler.BlockClassHandler;
import com.example.handler.FallbackClassHandler;
import com.example.service.SentinelClassService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-08 12:48
 * @apiNote TODO
 */
@Service
public class SentinelClassServiceImpl implements SentinelClassService {

    public static final String RESOURCE_CLASS = "selectClassById";

    // 注意：注解方式埋点不支持 private 方法。所以这里对应的类方法一定不能是私有方法的。
    //      @SentinelResource的value属性为资源名，在Sentinel仪表盘的簇点链路显示，也用于代码逻辑对流控、熔断规则时资源的指定。
    // TODO blockHandler是针对流控设置，超出规则，则会进入blockHandler方法。
    //      fallback是针对方法出现异常了，则会进入fallback方法。
    //  若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出BlockException时只会进入 blockHandler 处理逻辑。
    //  若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出。
    @SentinelResource(blockHandler = "selectClassByIdBlockHandler", blockHandlerClass = BlockClassHandler.class,
            fallbackClass = FallbackClassHandler.class, fallback = "selectClassByIdFallbackHandler")
    public String selectClassById(Integer id) {
        if (id == 2) {
            throw new RuntimeException("出异常了啊！");
        }
        return "{id: " + id + ", age: 25}";
    }
}
