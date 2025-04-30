package com.example.service;


import com.example.entity.TreeChildren;

import java.util.List;
import java.util.Set;

/**
 * 节点表(TreeChildren)表服务接口
 *
 * @author makejava
 * @since 2023-05-06 13:37:52
 */
public interface TreeChildrenService {

    Set<TreeChildren> findTreeBySetChildren();

    List<TreeChildren> findTreeByListChildren();
}
