package com.example.controller;

import com.example.entity.TreeChildren;
import com.example.service.TreeChildrenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-06 14:03
 * @apiNote TODO 接口类
 */
@Controller
@RequiredArgsConstructor
public class TreeController {

    private final TreeChildrenService treeChildrenService;

    @RequestMapping("/treeChildrenSet")
    public @ResponseBody Set<TreeChildren> TreeByJsonSet() {
        return treeChildrenService.findTreeBySetChildren();
    }

    @RequestMapping("/treeChildrenList")
    public @ResponseBody List<TreeChildren> TreeByJsonList() {
        return treeChildrenService.findTreeByListChildren();
    }
}
