package com.example.dddmessage.domain.aggregate.notice.entity.valueobject;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Data
@AllArgsConstructor
public class SocketMessage {
    private final int receiver;
    private final String channel;
    private final JSONObject data;
}