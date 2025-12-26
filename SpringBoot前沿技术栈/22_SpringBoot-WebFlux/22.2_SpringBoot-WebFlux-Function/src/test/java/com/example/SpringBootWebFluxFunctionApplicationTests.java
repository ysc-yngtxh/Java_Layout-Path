package com.example;

import com.example.config.WebConfig;
import com.example.dto.UserDto;
import com.example.router.UserRouter;
import com.example.handler.UserHandler;
import com.example.pojo.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// 1、@WebFluxTest 注解仅加载 WebFlux 相关的配置与文件，而不会像 @SpringBootTest 那样可以加载完整的应用上下文。
// 2、使用 @WebFluxTest 时，只会加载 @Controller, @RestController, @ControllerAdvice, WebTestClient, 实现WebFluxConfigurer的 所在类,
//    不会加载 @Service、@Component、@Repository 注解标注的类，如果需要使用这些类，可以使用 @MockBean 或 @Import 注解将它们导入测试上下文中。
// 3、@WebFluxTest注解默认会加载所有的 Controller 类，如果只想加载特定的 Controller，可以通过参数指定。
//    例如：@WebFluxTest(controllers = MyController.class)
@WebFluxTest
@Import({UserRouter.class, UserHandler.class, WebConfig.class}) // 导入 UserRouter、UserHandler 路由组件，以确保它在测试上下文中可用
public class SpringBootWebFluxFunctionApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRepository userRepository;


    @Test
    public void testGetAllUsers() {
        User user1 = new User(1, "user1", "user1@example.com", "User One", (byte) 0);
        User user2 = new User(2, "user2", "user2@example.com", "User Two", (byte) 1);

        when(userRepository.findAll()).thenReturn(Flux.just(user1, user2));

        // 该段代码意思是：
        //     测试 GET 请求 /api/users 接口，期望返回状态码 200 OK，响应头的内容类型为 application/json，
        //     响应体是一个包含两个 User 对象的列表，并且列表中包含 user1 和 user2。
        webTestClient.get().uri("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(User.class)
                .hasSize(2)
                .contains(user1, user2);
    }

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto(null, "newuser", "newuser@example.com", "New User", (byte) 1);
        User savedUser = new User(3, "newuser", "newuser@example.com", "New User", (byte) 0);

        when(userRepository.save(any(User.class))).thenReturn(Mono.just(savedUser));

        // 该段代码意思是：
        //     测试 POST 请求 /api/users 接口，发送一个新的 User 对象作为请求体，
        //     期望返回状态码 201 Created，响应体是保存后的 User 对象，并且该对象与 savedUser 相等。
        webTestClient.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(User.class)
                .isEqualTo(savedUser);
    }

}
