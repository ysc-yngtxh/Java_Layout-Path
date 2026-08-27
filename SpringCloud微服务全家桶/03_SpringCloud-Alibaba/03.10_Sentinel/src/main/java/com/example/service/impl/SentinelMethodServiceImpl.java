package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.service.SentinelMethodService;
import org.springframework.stereotype.Service;

@Service
public class SentinelMethodServiceImpl implements SentinelMethodService {

    public static final String RESOURCE_METHOD = "selectMethodById";

    // TODO @SentinelResource注解用于定义资源。加上该注解，Sentinel 才会对该资源进行流量监控。
    //      该注解提供可选的异常处理和 fallback 配置项，尽量应用于业务层(service)，属于资源名称限流。
    //  @SentinelResource：value属性是定义的资源名称，在Sentinel仪表盘的簇点链路显示，也用于代码逻辑对流控、熔断规则时资源的指定。
    //                     blockHandler属性是针对流控设置，超出规则，则会进入blockHandler方法。
    //                     fallback属性是针对方法出现异常了，则会进入fallback方法。
    //  若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出BlockException时只会进入 blockHandler 处理逻辑。
    //  若只配置 value属性，未配置 blockHandler、fallback等属性，当访问该资源被限流时，直接报错500。
    //  注意：注解方式埋点不支持 private 方法。所以注解里属性对应的类方法一定不能是私有方法的。
    @SentinelResource(value = RESOURCE_METHOD, blockHandler = "selectMethodByIdBlockHandler", fallback = "selectMethodByIdFallback")
    public String selectMethodById(Integer id) {
        if (id == 2) {
            throw new RuntimeException("出异常了啊！");
        }
        return "{id: " + id + ", age: 25}";
    }


    // Sentinel的 Method方式 限流，超出流量限制的请求会进入到blockHandler的方法。
    // 注意细节，一定要跟原函数的返回值和形参一致，并且形参最后要加个BlockException参数，否则会报错，FlowException: null
    public String selectMethodByIdBlockHandler(Integer Id, BlockException ex) {
        System.out.println("selectMethodByIdBlockHandler异常信息：" + ex.getMessage());
        return "{code:500, msg:" + Id + " -- 服务流量限流处理}";
    }


    // Sentinel的 Method方式 降级，服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public String selectMethodByIdFallback(Integer Id, Throwable throwable) {
        System.out.println("selectMethodByIdFallback异常信息：" + throwable.getMessage());
        return "{code: 500, msg: " + Id + " -- 服务熔断降级处理}";
    }
}
