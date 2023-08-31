package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情表(TbOrder)实体类
 * @author 游家纨绔
 * @since 2023-08-31 19:36:39
 */
@Data
@TableName(value = "order", keepGlobalPrefix = true)
public class TbOrder implements Serializable {
    @Serial
    private static final long serialVersionUID = -53428860068885420L;

    // 订单id
    private Long orderId;
    // sku商品id
    private Long skuId;
    // 购买数量
    private Integer num;
    // 购买价格
    private Double buyPrice;
    // 创建时间
    private Date createDate;
    // 更新时间
    private Date updatedDate;
    // 创建人
    private String createBy;
    // 更新人
    private String updatedBy;
    // 逻辑删除
    private Integer deleteFlag;

    // private TbSku sku;
}

