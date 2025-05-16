package com.example.service.impl;

import com.example.entity.TreeChildren;
import com.example.mapper.TreeChildrenMapper;
import com.example.service.TreeChildrenService;
import com.example.utils.TreeCommonUtil;
import com.example.utils.TreeRecursionUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 节点表(TreeChildren)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-05-06 13:00:00
 */
@Service
@RequiredArgsConstructor
public class TreeChildrenServiceImpl implements TreeChildrenService {

	private final TreeChildrenMapper treeChildrenMapper;

	public Set<TreeChildren> findTreeBySetRecursion() {
		List<TreeChildren> treeChildren = treeChildrenMapper.selectList(null);
		Set<TreeChildren> treeChildrenHashSet = new HashSet<>(treeChildren);
		return TreeRecursionUtil.buildTree(treeChildrenHashSet);
	}

	public List<TreeChildren> findTreeByListRecursion() {
		List<TreeChildren> treeChildren = treeChildrenMapper.selectList(null);
		return TreeRecursionUtil.buildMenuTree(treeChildren);
	}

	public List<TreeChildren> findTreeByListChildren() {
		List<TreeChildren> treeChildren = treeChildrenMapper.selectList(null);
		return TreeCommonUtil.buildTree(treeChildren);
	}

	public List<TreeChildren> findTreeWithStream() {
		List<TreeChildren> treeChildren = treeChildrenMapper.selectList(null);
		return TreeCommonUtil.listToTreeWithStream(treeChildren);
	}

	public List<TreeChildren> findTreeOptimized() {
		List<TreeChildren> treeChildren = treeChildrenMapper.selectList(null);
		return TreeCommonUtil.listToTreeOptimized(treeChildren);
	}
}
