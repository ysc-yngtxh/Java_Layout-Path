package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.vo.BaseEntity;
import lombok.Data;

import java.io.Serial;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 订单表(RocketOrder)实体类
 */
@Data
@TableName("ROCKET_ORDER")
public class RocketOrder extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 2371703871089055413L;

    /**
     * 订单id
     */
    @TableId("ORDER_ID")
    private Long orderId;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 订单金额
     */
    @TableField("ORDER_AMOUT")
    private Long orderAmout;

    /**
     * 发送消息类型
     */
    @TableField(exist = false)
    private Integer messageType;
}

