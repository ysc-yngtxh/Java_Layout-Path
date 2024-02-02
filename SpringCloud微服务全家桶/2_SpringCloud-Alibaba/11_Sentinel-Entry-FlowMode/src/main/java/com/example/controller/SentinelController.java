package com.example.controller;

import com.example.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SentinelController {

    @Autowired
    private EntryService entryService;

    // 定义了流控规则的接口 /getHandler
    @GetMapping("/getHandler")
    @ResponseBody
    public String getHandler() {
        return entryService.getHandler();
    }

    // 仅定义了熔断规则的接口 /getList，在业务逻辑中又通过资源实体获取了 /getHandler的流控资源
    @GetMapping("/getList")
    @ResponseBody
    public String getList(@RequestParam Integer num) {
        return entryService.getList(num);
    }

    //
    @GetMapping("/getInfo")
    @ResponseBody
    public String getInfo() {
        return entryService.getInfo();
    }
}