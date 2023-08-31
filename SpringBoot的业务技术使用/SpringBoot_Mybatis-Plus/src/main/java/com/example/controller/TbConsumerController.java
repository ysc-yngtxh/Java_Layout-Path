package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.TbConsumer;
import com.example.service.impl.TbConsumerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (TbConsumer)表控制层
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
@RestController
@RequestMapping("/tbConsumer")
@RequiredArgsConstructor
public class TbConsumerController {

    private final TbConsumerServiceImpl consumerService;

    @RequestMapping("/selectById")
    public TbConsumer selectById() {
        return consumerService.selectById();
    }

    @RequestMapping("/selectByIds")
    public List<TbConsumer> selectByIds() {
        return consumerService.selectByIds();
    }

    @RequestMapping("/selectByMap")
    public List<TbConsumer> selectByMap() {
        return consumerService.selectByMap();
    }

    @RequestMapping("/selectMaps")
    public List<Map<String, Object>> selectMaps() {
        return consumerService.selectMaps();
    }

    @RequestMapping("/selectByWrapper1")
    public List<TbConsumer> selectByWrapper1() {
        return consumerService.selectByWrapper1();
    }

    @RequestMapping("/selectByWrapper2")
    public List<TbConsumer> selectByWrapper2() {
        return consumerService.selectByWrapper2();
    }

    @RequestMapping("/selectByWrapper3")
    public List<TbConsumer> selectByWrapper3() {
        return consumerService.selectByWrapper3();
    }

    @RequestMapping("/selectByWrapper4")
    public List<TbConsumer> selectByWrapper4() {
        return consumerService.selectByWrapper4();
    }

    @RequestMapping("/selectByWrapper5")
    public List<TbConsumer> selectByWrapper5() {
        return consumerService.selectByWrapper5();
    }

    @RequestMapping("/selectByWrapper6")
    public List<TbConsumer> selectByWrapper6() {
        return consumerService.selectByWrapper6();
    }

    @RequestMapping("/selectByWrapper7")
    public List<TbConsumer> selectByWrapper7() {
        return consumerService.selectByWrapper7();
    }

    @RequestMapping("/selectByWrapper8")
    public List<TbConsumer> selectByWrapper8() {
        return consumerService.selectByWrapper8();
    }

    @RequestMapping("/selectByWrapper9")
    public List<TbConsumer> selectByWrapper9() {
        return consumerService.selectByWrapper9();
    }

    @RequestMapping("/selectByWrapper10")
    public List<TbConsumer> selectByWrapper10() {
        return consumerService.selectByWrapper10();
    }

    @RequestMapping("/selectByWrapper11")
    public List<TbConsumer> selectByWrapper11() {
        return consumerService.selectByWrapper11();
    }

    @RequestMapping("/condition")
    public List<TbConsumer> condition(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "email", required = false) String email) {
        return consumerService.condition(name, email);
    }

    @RequestMapping("/selectByWrapperEntity")
    public List<TbConsumer> selectByWrapperEntity() {
        return consumerService.selectByWrapperEntity();
    }

    @RequestMapping("/selectByWrapperAllEq")
    public List<TbConsumer> selectByWrapperAllEq() {
        return consumerService.selectByWrapperAllEq();
    }

    @RequestMapping("/selectByWrapperMap")
    public List<Map<String, Object>> selectByWrapperMap() {
        return consumerService.selectByWrapperMap();
    }

    @RequestMapping("/selectCustomAnnotation")
    public Map<String, List<TbConsumer>> selectCustomAnnotation() {
        return consumerService.selectCustomAnnotation();
    }

    @RequestMapping("/selectLambdaQuery")
    public List<TbConsumer> selectLambdaQuery() {
        return consumerService.selectLambdaQuery();
    }

    @RequestMapping("/selectPage")
    public Page<TbConsumer> selectPage() {
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
    public TbConsumer insertConsumer() {
        return consumerService.insertConsumer();
    }
}

