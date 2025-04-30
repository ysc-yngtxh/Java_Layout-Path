package com.example.entity;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 品牌表(Brand)实体类
 *
 * @author makejava
 * @since 2024-02-25 13:19:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    @Serial
    private static final long serialVersionUID = 914776956171371836L;

    /**
     * 主键Id
     */
    private Long Id;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 是否上架:0为true即上架,1为false下架
     */
    private Integer racking;
    /**
     * 逻辑删除：0存在,1删除
     */
    private Integer deleteFlag;
    /**
     * 备注
     */
    private String remark;
}
