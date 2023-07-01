package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 节点表(TreeChildren)实体类
 *
 * @author makejava
 * @since 2023-05-06 13:37:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "tree_children", autoResultMap = true)
public class TreeChildren implements Serializable {
    @Serial
    private static final long serialVersionUID = 413556348265113072L;

    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 地址
     */
    private String address;

    /**
     * 父节点Id
     */
    @TableField("parentId")  // 这里需要加上注解映射数据库中的字段名，否则sql语句中的返回字段将会是 parent_id
    private Integer parentId;

    /**
     * 备注
     */
    private String remake;

    /**
     * 排序
     */
    private Integer sort;

    private transient Set<TreeChildren> childrenSet = new HashSet<>();

    private transient List<TreeChildren> childrenList = new ArrayList<>();

}

