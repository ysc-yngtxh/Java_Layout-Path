package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情表(TbOrder)实体类
 * @author 游家纨绔
 * @since 2023-08-31 19:36:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order", keepGlobalPrefix = true)
public class TbOrder implements Serializable {
    @Serial
    private static final long serialVersionUID = -53428860068885420L;

    // 订单id。当我们的主键Id字段不是 Id 时，应该添加上@TableId注解进行认为映射主键
    // @TableId参数：value映射数据库column列主键字段；type设置新增数据主键Id规则,有自增、UUID等
    @TableId(value = "order_id", type = IdType.AUTO)
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

