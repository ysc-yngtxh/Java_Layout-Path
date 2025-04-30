package com.example.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系(TbBrand)实体类
 * @author 游家纨绔
 * @since 2023-09-02 14:48:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBrand implements Serializable {
    @Serial
    private static final long serialVersionUID = 968972510958796236L;

    /**
     * 品牌id
     */
    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌图片地址
     */
    private String image;
    /**
     * 品牌的首字母
     */
    private String letter;
}
