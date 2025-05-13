package com.example.controller;

import com.example.feign.UserClient;
import com.example.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 游家纨绔
 */
@Slf4j
@RestController
@RequestMapping("/user")
@DefaultProperties(defaultFallback = "ExecByIdAll") // 全局降级(定义降级方法)
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    // @Autowired
    // private DiscoveryClient discoveryClient; // 这里的是第二种情况下一个接口对象 -- Eureka注册中心对象

    // @Autowired
    // private RibbonLoadBalancerClient client; // 这里是第三种情况的内部实现对象 -- Ribbon负载均衡对象

    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Integer id) {

        // 第一种情况：只有提供者和消费者 -- 直连方式
        // String url = "http://localhost:8088/user/"+id;

        // 第二种情况：有提供者和消费者，还有注册中心Eureka
        //           在第一种情况下，要书写准确的 IP地址和端口。在集群的众多的服务下，费时费力地手动去更改，极其影响效率。
        //           所以我们需要一个注册中心去动态的获取IP地址和端口(但是拉取的服务是个集群，需要手动选择)
        // 根据服务id获取实例
        // List<ServiceInstance> instances = discoveryClient.getInstances("provider-service");
        // 从实例中取出ip和端口
        // ServiceInstance instance = instances.get(0);
        // String url = "http://" + instance.getHost()+ ":" + instance.getPort() + "/user/" +id;

        // 第三种情况：有提供者和消费者，还有注册中心Eureka，还加上一个负载均衡ribbon(用于服务集群的分配)
        // 根据服务id获取实例
        // ServiceInstance instance = client.choose("provider-service");
        // String url = "http://" + instance.getHost()+ ":" + instance.getPort() + "/user/" +id;
        // 这里可以直接将服务id写在url上，因为负载均衡在内部已经实现了上面两步得到地址
        // 这里的负载规则是采用轮询，随机，hash...等算法进行的

        String url = "http://provider-service/user/" + id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    // 降级实例
    @GetMapping("/exec/{id}")
    // @HystrixCommand(fallbackMethod = "ExecByIdAll")
    /* HystrixCommand注解是表示当前这个ExecByIdAll()方法出现异常时，会执行ExecByIdAll方法，这个就叫做服务降级。
       但是一定要注意，原方法和降级方法的返回值要是一样的*/
    /* 这样写是单独的给一个方法写一个降级处理。但是如果控制层有很多的方法，那就要一个一个的去写降级方法，太繁琐。
       所以我们可以在类上加注解 @DefaultProperties(defaultFallback = "ExcelByIdAll")，去指定全局的降级方法*/
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1800")
            // 这个注解表示的是手动设置超时时长，可以去hystrix包下的HystrixCommandProperties方法中看到
            // 有default_executionTimeoutInMilliseconds的常量值为1000毫秒，再点击可以查看到其方法的key
            // 但是这种注解还是只针对于当前方法有效，我们要向进行全局超时时长配置，就需要去我们的配置文件中进行配置
            // 当我们在配置文件中进行配置，并且在类上加上全局降级处理注解的时候，我们在方法上就只需加@HystrixCommand就行了
    })
    public String ExecById(@PathVariable("id") Integer id) {
        String url = "http://provider-service/user/" + id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }

    public String ExecByIdAll() {
        return "温馨提示：不好意思，服务器太拥挤了";
    }

    // 断路器实例
    @GetMapping("/count/{id}")
    @HystrixCommand(commandProperties = {
            // 断路器的请求阈值
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 断路器的休眠窗口
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 断路器的错误阈值百分比
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String ExecCount(@PathVariable("id") Integer id) {
        if (id % 2 == 0) {
            throw new RuntimeException("");
        }
        String url = "http://provider-service/user/" + id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }


    // Feign实例
    // 当你使用了Feign,你也就不需要RestTemplate了，
    // 像 @Autowired
    //    private RestTemplate restTemplate; 启动类的RestTemplate都可以删除
    @Autowired
    private UserClient userClient;

    @GetMapping("/feign/{id}")
    public User query(@PathVariable("id") Integer id) {
        // 利用Feign工具，就不需要再写
        // String url = "http://provider-service/user/" +id;
        // String user = restTemplate.getForObject(url, String.class);
        return userClient.queryByIdOnFeign(id);
    }
}
