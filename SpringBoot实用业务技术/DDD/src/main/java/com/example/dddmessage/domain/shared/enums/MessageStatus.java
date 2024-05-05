package com.example.dddmessage.domain.shared.enums;
import com.example.dddmessage.domain.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@AllArgsConstructor
@Getter
public enum MessageStatus implements ValueObject<MessageStatus> {
    /**
     * 正常 ，发送方接受方均可见
     */
    NORMAL((byte)0),
    /**
     * 在接受方的黑名单中 , 发送方可见,接收方不可见
     */
    IN_BLOCKED_LIST((byte)1),
    /**
     * 消息中包含违禁词 , 发送方可见,接收方不可见
     */
    HAS_STOP_WORD((byte)3),
    /**
     * 消息不安全 , 发送方可见,接收方不可见
     */
    UN_SAFE((byte)4),
    /**
     * 消息测回, 发送方、接收方均不可见
     */
    RECALL((byte)5);
    /**
     * 消息状态编码
     */

    private final byte code;

    @Override
    public boolean sameValueAs(MessageStatus other) {
        return other != null && other.getCode() == this.code;
    }
}
