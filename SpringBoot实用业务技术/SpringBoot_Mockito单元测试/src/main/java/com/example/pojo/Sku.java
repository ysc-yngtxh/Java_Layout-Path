package com.example.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-25 07:50
 * @apiNote TODO sku表(Sku)实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sku implements Serializable {
    @Serial
    private static final long serialVersionUID = 892788188735402403L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * spu的id
     */
    private Integer spuId;
    /**
     * 图片地址
     */
    private String img;
    /**
     * 标题
     */
    private String title;
    /**
     * 销售价格，单位为分
     */
    private BigDecimal price;
    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    private String ownSpec;
}
