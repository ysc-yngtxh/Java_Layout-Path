package com.youshicheng.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.youshicheng.Feign.UserClient;
import com.youshicheng.pojo.User;
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
@DefaultProperties(defaultFallback = "ExceByIdAll")//全局降级
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient; //这里的是第二种情况下一个接口对象

    //@Autowired
    //private RibbonLoadBalancerClient client;这里是第三种情况的内部实现对象

    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Integer id) {

        //第一种情况：只有提供者和消费者
        //String url = "http://localhost:8088/user/"+id;


        //第二种情况：有提供者和消费者，还有注册中心Eureka
        //在第一种情况下，要书写准确的IP地址和端口。在集群的众多的服务下，费时影费力地去更改响效率。
        //所以我们需要一个注册中心区动态的的获取IP地址和端口
        //根据服务id获取实例
        //List<ServiceInstance> instances = discoveryClient.getInstances("provice-service");
        //从实例中取出ip和端口
        //ServiceInstance instance = instances.get(0);
        //String url = "http://" + instance.getHost()+ ":" + instance.getPort() + "/user/" +id;

        //第三种情况：有提供者和消费者，还有注册中心Eureka，还加上一个负载均衡ribbon
        //根据服务id获取实例
        //ServiceInstance instance = client.choose("provice-service");
        //String url = "http://" + instance.getHost()+ ":" + instance.getPort() + "/user/" +id;
        //这里可以直接将服务id写在url上，因为负载均衡在内部已经实现了上面两步得到地址
        //这里的负载规则是采用轮询，随机，hash...等算法进行的
        String url = "http://provice-service/user/" + id;

        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    //降级实例
    @GetMapping("/exce/{id}")
    //@HystrixCommand(fallbackMethod = "ExceByIdAll")
    /*这个注解是表示当前这个ExceById方法出现异常时，会执行ExceByIdAll方法。这个就叫做服务降级。
      但是一定要注意，原方法和降级方法的返回值要是一样的*/
    /*这样写是单独的给一个方法写一个降级处理。但是如果控制层有很多的方法，那就要一个一个的去写降级方法，太繁琐。
      所以我们在类上加一个注解@DefaultProperties(defaultFallback = "ExceByIdAll")，去指定全局的降级方法*/
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1800")
            //这个注解表示的是手动设置超时时长，可以去hystrix包下的HystrixCommandProperties方法中看到
            //有default_executionTimeoutInMilliseconds的常量值为1000毫秒，再点击可以查看到其方法的key
            //但是这种注解还是只针对于当前方法有效，我们要向进行全局超时时长配置，就需要去我们的配置文件中进行配置
            //当我们在配置文件中进行配置，并且在类上加上全局降级处理注解的时候，我们在方法上就只需加@HystrixCommand就行了
    })
    public String ExceById(@PathVariable("id") Integer id) {
        String url = "http://provice-service/user/" + id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }

    public String ExceByIdAll() {
        return "温馨提示：不好意思，服务器太拥挤了";
    }

    //断路器实例
    @GetMapping("/cound/{id}")
    @HystrixCommand(commandProperties = {
            //断路器的请求阈值
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //断路器的休眠窗口
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //断路器的错误阈值百分比
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String ExceCound(@PathVariable("id") Integer id) {
        if (id % 2 == 0) {
            throw new RuntimeException("");
        }
        String url = "http://provice-service/user/" + id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }


    //Feign实例
    //当你使用了Feign,你也就不需要RestTemplate了，
    // 像@Autowired
    //   private RestTemplate restTemplate; 启动类的RestTemplate都可以删除
    @Autowired
    private UserClient userClient;

    @GetMapping("/ysc/{id}")
    public User query(@PathVariable("id") Integer id) {
        //利用Feign工具，就不需要再写
        //String url = "http://provice-service/user/" +id;
        //String user = restTemplate.getForObject(url,String.class);
        return userClient.queryByIdLL(id);
    }

}
