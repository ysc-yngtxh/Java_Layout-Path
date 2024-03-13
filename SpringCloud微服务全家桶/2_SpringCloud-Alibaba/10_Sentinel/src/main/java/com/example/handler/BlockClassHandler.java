package com.example.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-08 14:32
 * @apiNote TODO Sentinel的 Class方式 限流
 */
public class BlockClassHandler {

    // 超出流量限制的请求会进入到blockHandler的方法。
    // 注意细节，一定要跟原函数的返回值和形参一致，并且形参最后要加个BlockException参数，否则会报错，FlowException: null
    // 注意：降级逻辑方法一定要是静态方法。因为我们是没有注入容器的，所以Sentinel要想执行降级逻辑方法，是需要通过静态方法访问
    public static String selectClassByIdBlockHandler(Integer Id, BlockException ex) {
        System.out.println("selectClassByIdBlockHandler异常信息：" + ex.getMessage());
        return "{code:500, msg:" + Id + " -- 服务流量限流处理}";
    }
}
