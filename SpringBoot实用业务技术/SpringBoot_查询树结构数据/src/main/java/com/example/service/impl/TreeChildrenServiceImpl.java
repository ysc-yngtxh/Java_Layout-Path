package com.example.service.impl;

import com.example.dao.TreeChildrenDao;
import com.example.entity.TreeChildren;
import com.example.service.TreeChildrenService;
import com.example.utils.TreeChildrenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 节点表(TreeChildren)表服务实现类
 *
 * @author makejava
 * @since 2023-05-06 13:38:02
 */
@Service
@RequiredArgsConstructor
public class TreeChildrenServiceImpl implements TreeChildrenService {

    private final TreeChildrenDao treeChildrenDao;

    /**
     * @return Set<TreeChildren>
     */
    public Set<TreeChildren> findTreeBySetChildren(){
        List<TreeChildren> treeChildren = treeChildrenDao.selectList(null);
        Set<TreeChildren> treeChildrenHashSet = new HashSet<>(treeChildren);
        return TreeChildrenUtil.buildTree(treeChildrenHashSet);
    }

    /**
     * @return List<TreeChildren>
     */
    public List<TreeChildren> findTreeByListChildren(){
        List<TreeChildren> treeChildren = treeChildrenDao.selectList(null);
        return TreeChildrenUtil.buildMenuTree(treeChildren);
    }
}
