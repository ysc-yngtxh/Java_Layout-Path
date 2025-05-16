package com.example.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-13 12:00
 * @apiNote TODO
 */
@Data
@Builder
public class ConsumerInfo {

    private String queueName;

    private String data;
}
