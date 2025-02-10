package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 23:09
 * @apiNote TODO
 */
@Data
@Builder
@TableName("t_credit")
public class Credit implements Serializable {
    @Serial
    private static final long serialVersionUID = -7640959112343922498L;

    private Long id;

    // 用户id
    private Long userId;

    // 用户姓名
    private String username;

    // 积分
    private Integer integration;
}
