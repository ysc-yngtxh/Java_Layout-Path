package com.example.handler;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-08 14:57
 * @apiNote TODO Sentinel的 Class方式 熔断降级
 */
public class FallbackClassHandler {

    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    // 注意：降级逻辑方法一定要是静态方法。因为我们是没有注入容器的，所以Sentinel要想执行降级逻辑方法，是需要通过静态方法访问
    public static String selectClassByIdFallbackHandler(Integer id, Throwable throwable) {
        System.out.println("selectClassByIdFallback异常信息：" + throwable.getMessage());
        return "{code: 500, msg: " + id + " -- 服务熔断降级处理}";
    }
}
