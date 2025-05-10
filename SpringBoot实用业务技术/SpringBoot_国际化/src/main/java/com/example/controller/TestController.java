package com.example.controller;

import com.example.utils.I18nUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2023/3/10 21:00
 */
@Controller
@RequestMapping("/ysc")
public class TestController {

    @PostMapping("/test")
    public @ResponseBody String test() {
        return I18nUtil.getMessage("001", "喜欢", "总结");
    }

    @GetMapping("/test1")
    public @ResponseBody String test1() {
        return I18nUtil.getMessage("001", I18nUtil.getMessage("002"), I18nUtil.getMessage("003"));
    }
}
