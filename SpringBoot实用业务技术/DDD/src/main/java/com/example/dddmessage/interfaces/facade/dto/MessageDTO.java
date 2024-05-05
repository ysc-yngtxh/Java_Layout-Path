package com.example.dddmessage.interfaces.facade.dto;

import lombok.Data;
import java.util.Date;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Data
public class MessageDTO {
    private long messageId;
    private int catId;
    private int sender;
    private String senderNickname;
    private String senderPhoto;
    private int receiver;
    private String receiverNickname;
    private String receiverPhoto;
    private int contentTpl;
    private boolean read;
    private String content;
    private Date sendTime;
}
