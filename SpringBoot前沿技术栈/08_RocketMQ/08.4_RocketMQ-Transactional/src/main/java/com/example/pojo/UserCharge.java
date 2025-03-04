package com.example.pojo;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 21:00
 * @apiNote TODO 用户充值dto类
 */
@Data
public class UserCharge {

    // 用户id
    Long userId;

    // 充值金额
    BigDecimal chargeAmount;
}
