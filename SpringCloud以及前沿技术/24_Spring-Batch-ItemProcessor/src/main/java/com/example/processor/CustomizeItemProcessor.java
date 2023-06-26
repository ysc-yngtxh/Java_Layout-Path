package com.example.processor;

import com.example.dto.User;
import lombok.NonNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-16 00:07
 * @apiNote TODO 自定义处理器
 */
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class CustomizeItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(@NonNull User item) throws Exception {
        // 将id为偶数数据获取，其他的放弃--返回null
        User user = item.getId() % 2 == 0 ? item : null;

        // 将过滤好的数据的name属性值转为大写
        Optional.ofNullable(user).ifPresent(str -> {
            user.setName( user.getName().toUpperCase() );
        });
        return user;
    }
}

