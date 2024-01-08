package com.example.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-08 14:32
 * @apiNote TODO
 */
public class BlockClassHandler {

    // 超出流量限制的请求会进入到blockHandler的方法。
    // 注意细节，一定要跟原函数的返回值和形参一致，并且形参最后要加个BlockException参数，否则会报错，FlowException: null
    public String selectClassByIdBlockHandler(Integer Id, BlockException ex) {
        System.out.println("selectClassByIdBlockHandler异常信息：" + ex.getMessage());
        return "{code:500, msg:" + Id + "服务流量控制处理}";
    }
}
