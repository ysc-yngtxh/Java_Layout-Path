package com.example.openfeign;

import com.example.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-15 00:00
 * @apiNote TODO
 */
// @FeignClient表示启用OpenFeign组件，value值是服务ip，fallback是启动熔断服务。UserCircuitFeignImpl就是熔断后的降级处理。
@FeignClient(value = "nacos-discovery-provider", path = "/provider", fallback = UserCircuitFeignImpl.class)
public interface UserCircuitFeign {

    @GetMapping("/{id}")
    User queryById(@PathVariable("id") Integer id);

    // OpenFeign默认使用的是 HttpURLConnection 进行底层调用服务的。
    // 但是由于JDK的 URLConnection 不支持连接池，通信效率很低，所以生产中是不会使用该默认实现的。
    // 可以通过自己在pom文件中引入 OkHttp 作为底层的通信工具。
}
