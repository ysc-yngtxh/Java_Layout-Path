package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.service.SentinelService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 16:00
 * @apiNote TODO
 */
@Service
public class SentinelServiceImpl implements SentinelService {

    public static final String GET_HANDLER = "handler";
    public static final String GET_LIST = "list";

    // 该`GET_HANDLER`资源具有授权规则
    @SentinelResource(value = GET_HANDLER, blockHandler = "getHandlerBlockHandler")
    public String getHandler() {
        return "定义了 getHandler 在业务层的资源名！！！";
    }
    public String getHandlerBlockHandler(BlockException ex) {
        System.out.println("getHandlerBlockHandler异常信息：" + ex.getMessage());
        return "{code: 500, msg: 服务流量限流处理}";
    }


    // 该`GET_LIST`资源具有热点规则
    @SentinelResource(value = GET_LIST, blockHandler = "getListBlockHandler")
    public String getList(Integer id, String username) {
        return "定义了 getList 在业务层的资源名！！！";
    }
    public String getListBlockHandler(Integer id, String username, BlockException ex) {
        System.out.println("getListsBlockHandler异常信息：" + ex.getMessage());
        return "{code: 500, msg: 服务流量限流处理}";
    }
}
