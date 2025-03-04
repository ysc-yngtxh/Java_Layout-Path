package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 21:00
 * @apiNote TODO RocketMQ事务日志表
 */
@Data
@Builder
@TableName("t_mq_transaction_log")
public class MQTxLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 5725561784226415175L;

    // 事务id
    private String transactionId;

    // 日志
    private String log;
}
