package com.example.dddmessage.interfaces.facade.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageCommand {
    private long messageId;
    private int catId;
    private int sender;
    private int receiver;
    private String content;

    @Override
    public String toString() {
        return "SendMessageCommand{" +
                "messageId=" + messageId +
                ", catId=" + catId +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                '}';
    }
}
