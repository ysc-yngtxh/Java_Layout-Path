package com.example.dddmessage.infrastructure.client;

import com.example.dddmessage.domain.aggregate.message.entity.valueobject.User;
import com.example.dddmessage.domain.shared.facade.UserServiceFacade;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Component
public class UserServiceFacadeClient implements UserServiceFacade {

    @Override
    public User getUser(int userId) {
        return new User(userId, "mock_nickname_" + userId, "mock_photo_" + userId);
    }

    /**
     * 可内嵌一个feignClient调用服务
     */
    //@FeignClient(serviceId = "user-service")
    //interface UserServiceInterface{
    //    Response<XXX> getUserById(@PathVariable int userId);
    //}
}
