package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Consumer;
import com.example.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (Consumer)表控制层
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
@RestController
@RequestMapping("/tbConsumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

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

    @RequestMapping("/selectMaps")
    public List<Map<String, Object>> selectMaps() {
        return consumerService.selectMaps();
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

    @RequestMapping("/selectByWrapperAllEq")
    public List<Consumer> selectByWrapperAllEq() {
        return consumerService.selectByWrapperAllEq();
    }

    @RequestMapping("/selectByWrapperMap")
    public List<Map<String, Object>> selectByWrapperMap() {
        return consumerService.selectByWrapperMap();
    }

    @RequestMapping("/selectCustomAnnotation")
    public Map<String, List<Consumer>> selectCustomAnnotation() {
        return consumerService.selectCustomAnnotation();
    }

    @RequestMapping("/selectLambdaQuery")
    public List<Consumer> selectLambdaQuery() {
        return consumerService.selectLambdaQuery();
    }

    @RequestMapping("/selectPage")
    public Page<Consumer> selectPage() {
        return consumerService.selectPage();
    }

    @RequestMapping("/updateConsumer")
    public void updateConsumer() {
        consumerService.updateConsumer();
    }

    @RequestMapping("/deleteConsumer")
    public void deleteConsumer() {
        consumerService.deleteConsumer();
    }

    @RequestMapping("/insertConsumer")
    public Consumer insertConsumer() {
        return consumerService.insertConsumer();
    }
}
