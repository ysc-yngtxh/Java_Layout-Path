package com.example.handler;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-08 14:57
 * @apiNote TODO
 */
public class FallbackClassHandler {

    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public String selectClassByIdFallbackHandler(Integer id, Throwable throwable) {
        System.out.println("selectClassByIdFallback异常信息：" + throwable.getMessage());
        return "{code: 500, msg: " + id + "服务熔断降级处理}";
    }
}
