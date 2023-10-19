package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.vo.BaseEntity;
import lombok.Data;

import java.io.Serial;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 积分表(RocketPoints)实体类
 */
@Data
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

