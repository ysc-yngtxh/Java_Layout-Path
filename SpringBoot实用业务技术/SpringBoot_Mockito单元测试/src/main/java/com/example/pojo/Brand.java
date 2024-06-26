package com.example.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 品牌表(Brand)实体类
 *
 * @author 游家纨绔
 * @since 2024-06-26 22:10:11
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {

    private static final long serialVersionUID = 326235033000435685L;

    /**
     * 主键Id
     */
    private Long id;
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

