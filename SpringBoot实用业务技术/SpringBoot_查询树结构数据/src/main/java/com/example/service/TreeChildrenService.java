package com.example.service;

import com.example.entity.TreeChildren;
import java.util.List;
import java.util.Set;

/**
 * 节点表(TreeChildren)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-05-06 13:00:00
 */
public interface TreeChildrenService {

	Set<TreeChildren> findTreeBySetRecursion();

	List<TreeChildren> findTreeByListRecursion();

	List<TreeChildren> findTreeByListChildren();

	List<TreeChildren> findTreeWithStream();

	List<TreeChildren> findTreeOptimized();
}
