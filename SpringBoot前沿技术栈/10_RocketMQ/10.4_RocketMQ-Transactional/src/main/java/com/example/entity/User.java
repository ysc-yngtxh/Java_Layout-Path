package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 23:09
 * @apiNote TODO
 */
@Data
@Builder
@TableName("t_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 2558291062144434794L;

    private Long id;

    // 姓名
    private String name;

    // 身份证号
    private String idCard;

    // 余额
    private BigDecimal balance;

    // 状态（1在线，0离线）
    private boolean state;

    // VIP用户标识（1是，0否）
    private boolean vipFlag;

    // 创建时间
    private Date createTime;

    // 最后一次登录时间
    private Date lastLoginTime;
}
