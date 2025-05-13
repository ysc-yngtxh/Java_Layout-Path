package com.example.controller;

import com.example.openfeign.UserCircuitFeign;
import com.example.pojo.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 游家纨绔
 */
@Slf4j  // 用于打印日志和设置日志级别
@RestController
@RequestMapping("/feign")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserCircuitFeign userCircuitFeign;

    @Autowired
    private DiscoveryClient discoveryClient;  // 注册中心客户端对象

    // 直连方式 -- 固定的Ip、端口（需要定义restTemplate，底层使用JDK的 URLConnection通信）
    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id) {
        // TODO 测试直连方式的时候，需要把启动类中的 RestTemplate 注解@LoadBalanced删除掉
        return restTemplate.getForObject("http://localhost:8081/provider/"+id, User.class);
    }

    // 微服务方式 -- 动态Ip、端口（需要定义负载均衡@LoadBalanced和restTemplate，底层使用JDK的 URLConnection通信）
    @GetMapping("/micro/{id}")
    public User queryTemplate(@PathVariable("id") Integer id) {
        return restTemplate.getForObject("http://nacos-provider/provider/"+id, User.class);
    }

    // OpenFeign方式 -- 伪客户端（不需要定义restTemplate，OpenFeign底层使用自定义配置的OkHttp通信）
    @GetMapping("/openfeign/{id}")
    public User queryOpenFeign(@PathVariable("id") Integer id) {
        return userCircuitFeign.queryById(id);
    }

    // CircuitBreaker熔断器。@CircuitBreaker 的降级逻辑需要方法内部抛出异常才会生效。
    // 如果 Feign 在调用前抛出的 ServiceUnavailableException（因找不到实例），此时 Feign 还未执行实际方法，因此断路器未拦截到异常。
    @GetMapping("/circuitBreaker")
    @CircuitBreaker(name = "nacos-discovery-provider", fallbackMethod = "circuitFallback")
    public String queryCircuitBreaker() {
        return userCircuitFeign.queryTime();
    }
    // circuitFallback是服务降级后的兜底处理方法
    public String circuitFallback(Throwable throwable) {
        return "circuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~\n" +
               "异常信息：" + throwable.getMessage();
    }

    @GetMapping("/discovery")
    public List<String> discoveryHandle() {
        // 获取注册中心所有服务名称
        List<String> services = discoveryClient.getServices();
        for (String serviceName : services) {
            // 获取指定微服务名称的所有微服务实例
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            for (ServiceInstance instance : instances) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("serviceName", serviceName);
                map.put("serviceId", instance.getServiceId());
                map.put("serviceHost", instance.getHost());
                map.put("servicePort", instance.getPort());
                map.put("uri", instance.getUri());
                System.out.println(map);
            }
        }
        return services;
    }
}
