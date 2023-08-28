package com.example.controller;

import com.example.entity.Consumer;
import com.example.service.impl.ConsumerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (Consumer)表控制层
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerServiceImpl consumerService;

    @RequestMapping("/selectById")
    public Consumer selectById() {
        return consumerService.selectById();
    }

    @RequestMapping("/selectByIds")
    public List<Consumer> selectByIds() {
        return consumerService.selectByIds();
    }

    @RequestMapping("/selectByMap")
    public List<Consumer> selectByMap() {
        return consumerService.selectByMap();
    }

    @RequestMapping("/selectByWrapper1")
    public List<Consumer> selectByWrapper1() {
        return consumerService.selectByWrapper1();
    }

    @RequestMapping("/selectByWrapper2")
    public List<Consumer> selectByWrapper2() {
        return consumerService.selectByWrapper2();
    }

    @RequestMapping("/selectByWrapper3")
    public List<Consumer> selectByWrapper3() {
        return consumerService.selectByWrapper3();
    }

    @RequestMapping("/selectByWrapper4")
    public List<Consumer> selectByWrapper4() {
        return consumerService.selectByWrapper4();
    }

    @RequestMapping("/selectByWrapper5")
    public List<Consumer> selectByWrapper5() {
        return consumerService.selectByWrapper5();
    }

    @RequestMapping("/selectByWrapper6")
    public List<Consumer> selectByWrapper6() {
        return consumerService.selectByWrapper6();
    }

    @RequestMapping("/selectByWrapper7")
    public List<Consumer> selectByWrapper7() {
        return consumerService.selectByWrapper7();
    }

    @RequestMapping("/selectByWrapper8")
    public List<Consumer> selectByWrapper8() {
        return consumerService.selectByWrapper8();
    }

    @RequestMapping("/selectByWrapper9")
    public List<Consumer> selectByWrapper9() {
        return consumerService.selectByWrapper9();
    }

    @RequestMapping("/selectByWrapper10")
    public List<Consumer> selectByWrapper10() {
        return consumerService.selectByWrapper10();
    }

    @RequestMapping("/selectByWrapper11")
    public List<Consumer> selectByWrapper11() {
        return consumerService.selectByWrapper11();
    }

    @RequestMapping("/condition")
    public List<Consumer> condition(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "email", required = false) String email) {
        return consumerService.condition(name, email);
    }

    @RequestMapping("/selectByWrapperEntity")
    public List<Consumer> selectByWrapperEntity() {
        return consumerService.selectByWrapperEntity();
    }
}

