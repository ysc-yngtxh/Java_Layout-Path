package com.example.utils;

import com.example.entity.TreeChildren;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-06 14:51
 * @apiNote TODO 解析节点工具类
 */
public class TreeChildrenUtil {

    /**
     * 使用递归方法解析List集合数据节点
     * @param allNodes
     * @return
     */
    public static Set<TreeChildren> buildTree(Set<TreeChildren> allNodes) {
        // 根节点
        Set<TreeChildren> root = new HashSet<>();
        allNodes.forEach(node -> {
            if (node.getParentId() == 0) {
                root.add(node);
            }
        });
        root.forEach(node -> {
            findChildren(node, allNodes);
        });
        return root;
    }


    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private static TreeChildren findChildren(TreeChildren treeNode, Set<TreeChildren> treeNodes) {
        for (TreeChildren it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                treeNode.getChildrenSet().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }


    /**
     * 使用递归方法解析List集合数据节点，并根据Id排序
     * @param allNodes
     * @return
     */
    public static List<TreeChildren> buildMenuTree(List<TreeChildren> allNodes) {
        // 根节点
        List<TreeChildren> root = new ArrayList<>();
        allNodes.forEach(node -> {
            if (node.getParentId() == 0) {
                root.add(node);
            }
        });
        root.forEach(node -> {
            findMenuChildren(node, allNodes);
        });

        // 对根节点排序
        List<TreeChildren> sortedList = root.stream().sorted( Comparator.comparing( TreeChildren::getSort ) ).toList();
        // 先清空，在添加
        root.clear();
        root.addAll( sortedList );
        return root;
    }


    /**
     * 递归查找子节点，并根据id排序
     * @param treeNodes
     * @return
     */
    private static TreeChildren findMenuChildren(TreeChildren treeNode, List<TreeChildren> treeNodes) {
        for (TreeChildren it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                treeNode.getChildrenList().add(findMenuChildren(it, treeNodes));
            }
        }
        // 对子节点排序
        List<TreeChildren> childrenSorted = treeNode.getChildrenList().stream().sorted( Comparator.comparing( TreeChildren::getSort ) ).toList();
        // 先清空，在添加
        treeNode.getChildrenList().clear();
        treeNode.getChildrenList().addAll( childrenSorted );
        return treeNode;
    }
}
