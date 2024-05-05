package com.example.dddmessage.domain.aggregate.message.entity.valueobject;

import com.example.dddmessage.domain.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements ValueObject<User> {
    private int userId;
    private String nickname;
    private String photo;

    @Override
    public boolean sameValueAs(User other) {
        return other != null && new EqualsBuilder().
                append(this.userId, other.userId).
                append(this.nickname, other.nickname).
                append(this.photo, other.photo).
                isEquals();
    }
}