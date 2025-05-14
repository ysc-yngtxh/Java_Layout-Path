package com.example.controller;

import com.example.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @Autowired
    private EntryService entryService;

    // 定义了流控规则的接口 /getHandler
    @GetMapping("/getHandler")
    public String getHandler() {
        return entryService.getHandler();
    }


    // 仅定义了熔断规则的接口 /getList，在业务逻辑中又通过资源实体获取了 /getHandler的流控资源
    @GetMapping("/getList")
    public String getList(@RequestParam Integer num) {
        return entryService.getList(num);
    }


    // 流控来源： default、自定义、other
    // default：不区分调用来源，即任何来源都可以匹配流控规则
    // 自定义：只有符合指定的自定义流控来源时，其资源才能匹配流控规则
    // other：同一个资源有多个流控来源时，表示除开已经设置好的其他流控来源
    @GetMapping("/getInfo")
    public String getInfo() {
        // 请求该接口时可选择性的带上参数name值：
        // DepartRequestOriginParser类中实现重写parseOrigin()方法，有自定义的根据参数返回请求来源的逻辑
        // 不带name请求参数：即该接口请求来源为parseOrigin()方法的返回值“ysc”
        // 带name请求参数：即返回相应的参数作为请求来源
        return entryService.getInfo();
    }
}
