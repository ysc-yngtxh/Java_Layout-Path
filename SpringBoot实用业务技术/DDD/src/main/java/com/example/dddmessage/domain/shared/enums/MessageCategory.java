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
public enum MessageCategory implements ValueObject<MessageCategory> {
    /**
     * 聊天消息
     */
    CHAT(0),
    /**
     * 新订单
     */
    CREATED_ORDER(1),
    /**
     * 买家已付款
     */
    PAID(2);

    private final int code;

    /**
     * 通过分类编码获取消息分类
     * @param code
     * @return
     */
    public static MessageCategory getByCode(int code){
        for (MessageCategory category : MessageCategory.values()){
            if(category.getCode() == code){
                return category;
            }
        }
        return null;
    }

    @Override
    public boolean sameValueAs(MessageCategory other) {
        return other != null && other.getCode() == this.code;
    }
}