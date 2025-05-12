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
 * @dateTime 2023-05-06 14:00
 * @apiNote TODO 接口类
 */
@Controller
@RequiredArgsConstructor
public class TreeController {

    private final TreeChildrenService treeChildrenService;

    @RequestMapping("/RecursionSet")
    public @ResponseBody Set<TreeChildren> TreeByJsonSet() {
        return treeChildrenService.findTreeBySetRecursion();
    }

    @RequestMapping("/RecursionList")
    public @ResponseBody List<TreeChildren> TreeByJsonList() {
        return treeChildrenService.findTreeByListRecursion();
    }

    @RequestMapping("/treeList")
    public @ResponseBody List<TreeChildren> TreeByJsonList2() {
        return treeChildrenService.findTreeByListChildren();
    }

    @RequestMapping("/treeListStream")
    public @ResponseBody List<TreeChildren> TreeByJsonList3() {
        return treeChildrenService.findTreeWithStream();
    }

    @RequestMapping("/treeListOptimized")
    public @ResponseBody List<TreeChildren> TreeByJsonList4() {
        return treeChildrenService.findTreeOptimized();
    }
}
