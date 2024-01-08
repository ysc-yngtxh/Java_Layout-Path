package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.service.SentinelMethodService;
import org.springframework.stereotype.Service;

@Service
public class SentinelMethodServiceImpl implements SentinelMethodService {

    // fallback是针对方法出现异常了，则会进入fallback方法。
    // blockHandler是针对流控设置，超出规则，则会进入blockHandler方法。
    // 若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出BlockException时只会进入 blockHandler 处理逻辑。
    // 若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出。

    public static final String RESOURCE_NAME = "selectUserById";

    @Override
    // 注意：注解方式埋点不支持 private 方法。
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "selectUserByIdBlockHandler",
            fallback = "selectUserByIdFallback")
    public String selectUserById(Integer Id) {
        if (Id == 2) {
            throw new RuntimeException("出异常了啊！");
        }
        return "{id: " + Id + ", age: 25}";
    }


    // 超出流量限制的请求会进入到blockHandler的方法。
    // 注意细节，一定要跟原函数的返回值和形参一致，并且形参最后要加个BlockException参数，否则会报错，FlowException: null
    public String selectUserByIdBlockHandler(Integer Id, BlockException ex) {
        System.out.println("selectUserByIdBlockHandler异常信息：" + ex.getMessage());
        return "{code:500, msg:" + Id + "服务流量控制处理}";
    }


    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public String selectUserByIdFallback(Integer Id, Throwable throwable) {
        System.out.println("selectUserByIdFallback异常信息：" + throwable.getMessage());
        return "{code: 500, msg: " + Id + "服务熔断降级处理}";
    }
}