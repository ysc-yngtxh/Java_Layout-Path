package com.example.controller;

import com.example.pojo.User;
import com.example.openService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * @author 游家纨绔
 */
@Slf4j  // 用于打印日志和设置日志级别
@RestController
@RequestMapping("/consumer")
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;  // 注册中心客户端对象

    @Autowired
    private UserService userService;

    // 直连方式 -- 固定的Ip、端口(需要定义restTemplate，底层使用JDK的 URLConnection通信)
    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id) {
        // TODO 测试直连方式的时候，需要把启动类中的 RestTemplate 注解@LoadBalanced删除掉
        return restTemplate.getForObject("http://localhost:8081/provider/"+id, User.class);
    }

    // 微服务方式 -- 动态Ip、端口(需要定义负载均衡@LoadBalanced和restTemplate，底层使用JDK的 URLConnection通信)
    @GetMapping("/template/{id}")
    public User queryTemplate(@PathVariable("id") Integer id) {
        return restTemplate.getForObject("http://nacos-provider/provider/"+id, User.class);
    }

    // OpenFeign方式 -- 伪客户端(不需要定义restTemplate，底层使用HttpClient通信)
    @GetMapping("/openfeign/{id}")
    public User queryOpenFeign(@PathVariable("id") Integer id) {
        return userService.queryById(id);
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
