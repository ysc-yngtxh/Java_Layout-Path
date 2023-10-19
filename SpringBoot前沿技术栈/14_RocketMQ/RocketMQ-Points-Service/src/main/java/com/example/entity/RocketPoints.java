package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 积分表(RocketPoints)实体类
 */
@Data
// 不加该注解的影响：子类对象A和对象B属性值一致，但其继承的父类对象属性值不一致，在比较的时候 Objects.equals(A,B) 结果为 true。
@EqualsAndHashCode(callSuper = true)  // 将其父类属性也进行比较
@TableName("ROCKET_POINTS")
public class RocketPoints extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 263231234846351862L;

    /**
     * 积分id
     */
    private Long pointsId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 积分
     */
    private Integer points;
}

