package com.example.controller;

import com.example.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SentinelController {

    @Autowired
    private EntryService entryService;

    // 自定义URL资源异常处理逻辑
    @GetMapping("/getHandler")
    @ResponseBody
    public String getHandler() {
        return entryService.getHandler();
    }

    @GetMapping("/getList")
    @ResponseBody
    public String getList() {
        return entryService.getList();
    }
}