package com.example.feign;

import com.example.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 游家纨绔
 */
// 这个注解启用Feign组件，value值是服务ip，fallback是启动熔断服务，这个时候UserClientImpl就是熔断后的降级处理
@FeignClient(value = "provider-service", fallback = UserClientImpl.class)
public interface UserClient {

    @GetMapping("/user/{id}") // 这个注解是用来拼接服务IP的，告诉Feign你的服务提供者地址
    User queryByIdOnFeign(@PathVariable("id") Integer id);

    /* 流程就相当于是先去执行 FeignClient中的 http://provider-service/user/{id},
     * 如果这个链接接口访问失败就会执行降级处理 UserClientImpl.class 中的 queryByIdOnFeign()方法
     *
     * 在 provider-service 服务里加了一个延迟来模拟超时异常，所以在请求这个服务时候会直接触发降级处理。
     * 去掉延迟，服务正常访问！！！
     */
}
