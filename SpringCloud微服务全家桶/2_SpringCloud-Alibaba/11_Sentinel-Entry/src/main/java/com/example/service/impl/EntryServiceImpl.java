package com.example.service.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.service.EntryService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 16:07
 * @apiNote TODO
 */
@Service
public class EntryServiceImpl implements EntryService {

    public static final String GET_HANDLER = "handler";
    public static final String GET_LIST = "list";

    @SentinelResource(value = GET_HANDLER, blockHandler = "getHandlerBlockHandler")
    public String getHandler() {
        return "定义了 getHandler 在业务层的资源名！！！";
    }

    public String getHandlerBlockHandler(Integer Id, BlockException ex) {
        System.out.println("getHandlerBlockHandler异常信息：" + ex.getMessage());
        return "{code:500, msg:" + Id + " -- 服务流量限流处理}";
    }



    @SentinelResource(value = GET_LIST, fallback = "getListFallback")
    public String getList() {
        Entry entry = null;
        try {
            entry = SphU.entry(GET_HANDLER);
        } catch (BlockException e) {
            return "定义了 getList 在业务层的资源名！！！";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return "定义了 getList 在业务层的资源名！！！";
    }

    public String getListFallback(Integer Id, Throwable throwable) {
        System.out.println("getListFallback异常信息：" + throwable.getMessage());
        return "{code: 500, msg: " + Id + " -- 服务熔断降级处理}";
    }
}
