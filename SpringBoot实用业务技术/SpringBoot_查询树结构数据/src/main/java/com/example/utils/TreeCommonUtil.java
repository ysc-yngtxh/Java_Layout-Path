package com.example.utils;

import com.example.entity.TreeChildren;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-12 11:40:00
 */
public class TreeCommonUtil {

	/**
	 * 使用非递归方法解析List集合数据节点
	 */
	public static List<TreeChildren> buildTree(List<TreeChildren> nodes) {
		List<TreeChildren> treeList = new ArrayList<>();
		Map<Integer, TreeChildren> nodeMap = new HashMap<>();

		// 第一次遍历：将所有节点存入map
		for (TreeChildren node : nodes) {
			nodeMap.put(node.getId(), node);
		}

		// 第二次遍历：建立父子关系
		for (TreeChildren node : nodes) {
			Integer parentId = node.getParentId();
			if (parentId == 0) {
				// 根节点
				treeList.add(node);
			} else {
				// 子节点，添加到父节点的children列表
				TreeChildren parent = nodeMap.get(parentId);
				if (parent != null) {
					parent.getChildrenList().add(node);
				}
			}
		}

		return treeList;
	}

	/**
	 * 使用Java 8 Stream API简化实现
	 */
	public static List<TreeChildren> listToTreeWithStream(List<TreeChildren> list) {
		Map<Integer, TreeChildren> nodeMap =
				list.stream().collect(Collectors.toMap(TreeChildren::getId, node -> node));

		list.forEach(node -> {
			Integer parentId = node.getParentId();
			if (parentId != 0) {
				TreeChildren parent = nodeMap.get(parentId);
				if (parent != null) {
					parent.getChildrenList().add(node);
				}
			}
		});

		return list.stream()
		           .filter(node -> node.getParentId() == 0)
		           .collect(Collectors.toList());
	}

	/**
	 * 处理大数据量的优化版本(使用并发流)
	 */
	public static List<TreeChildren> listToTreeOptimized(List<TreeChildren> list) {
		// 使用并发安全的Map
		ConcurrentMap<Integer, TreeChildren> nodeMap =
				list.parallelStream().collect(Collectors.toConcurrentMap(TreeChildren::getId, node -> node));

		list.parallelStream().forEach(node -> {
			Integer parentId = node.getParentId();
			if (parentId != 0) {
				TreeChildren parent = nodeMap.get(parentId);
				if (parent != null) {
					synchronized (parent) {  // 确保线程安全
						parent.getChildrenList().add(node);
					}
				}
			}
		});

		return list.parallelStream()
		           .filter(node -> node.getParentId() == 0)
		           .collect(Collectors.toList());
	}
}
