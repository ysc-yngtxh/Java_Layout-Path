package com.example.service.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.service.EntryService;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 16:00
 * @apiNote TODO
 */
@Service
public class EntryServiceImpl implements EntryService {

    public static final String GET_HANDLER = "handler";
    public static final String GET_LIST = "list";
    public static final String GET_INFO = "info";

    // 该`GET_HANDLER`资源具有流控规则
    @SentinelResource(value = GET_HANDLER, blockHandler = "getHandlerBlockHandler")
    public String getHandler() {
        return "定义了 getHandler 在业务层的资源名！！！";
    }
    public String getHandlerBlockHandler(BlockException ex) {
        System.out.println("getHandlerBlockHandler异常信息：" + ex.getMessage());
        return "{code: 500, msg: 服务流量限流处理}";
    }


    // 该`GET_LIST`资源既有自身的熔断规则，又通过资源实体获取了`GET_HANDLER`资源的流控规则
    @SentinelResource(value = GET_LIST, fallback = "getListFallback")
    @SneakyThrows
    public String getList(Integer num) {
        // TODO 三种控制资源规则的方式
        //    1、通过Sentinel控制台  2、通过代码API设置(Flow,DegradeRule)  3、通过资源实体Entry
        Entry entry = null;
        try {
            entry = SphU.entry(GET_HANDLER);
        } catch (BlockException e) {
            return "getList 在业务层出现异常，出现了限流规则";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        // 这里通过获取请求参数设置睡眠时间，用以触发熔断机制
        TimeUnit.SECONDS.sleep(num);
        return "定义了 getList 在业务层的资源名！！！";
    }
    public String getListFallback(Integer num, Throwable throwable) {
        System.out.println("getListFallback异常信息：" + throwable.getMessage());
        return "{code: 500, msg: 服务熔断降级处理}";
    }


    @SentinelResource(value = GET_INFO, blockHandler = "getHandlerBlockHandler2")
    public String getInfo() {
        return "定义了 getInfo 在业务层的资源名！！！";
    }
    public String getHandlerBlockHandler2(BlockException ex) {
        System.out.println("getHandlerBlockHandler2异常信息：" + ex.getMessage());
        return "{code: 500, msg: 服务流量限流处理}";
    }
}
