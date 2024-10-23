package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 订单表(RocketOrder)实体类
 */
@Data
// 不加该注解的影响：子类对象A和对象B属性值一致，但其继承的父类对象属性值不一致，在比较的时候 Objects.equals(A,B) 结果为 true。
@EqualsAndHashCode(callSuper = true)  // 将其父类属性也进行比较
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

