package com.example.filterFactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 14:31
 * @apiNote TODO
 */
@Component
@Slf4j
public class OneGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    // 客户端向Spring Cloud Gateway发出请求。如果网关处理程序映射确定请求与路由匹配，则将其发送给网关Web处理程序。
    // 该处理程序通过特定于该请求的过滤器链运行请求。过滤器用虚线分隔的原因是，过滤器可以在发送代理请求之前和之后运行逻辑。
    // 所有“预”过滤器逻辑都会被执行。然后发出代理请求。在发出代理请求之后，将运行“post”过滤器逻辑。
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            // pre-filter “预”过滤器逻辑
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            log.info(config.getName() + "-" + config.getValue() + " - pre 开始执行的时间：" + System.currentTimeMillis());
            return chain.filter(exchange).then(
                    // post-filter 在发出代理请求之后，再运行“post”过滤器逻辑。
                    Mono.fromRunnable(() -> { // 创建一个Mono对象，它表示一个延迟执行的任务。
                        stopWatch.stop();
                        log.info(config.getName() + "-" + config.getValue() + " - post 执行时间(毫秒):" + System.currentTimeMillis());
                        log.info(config.getName() + "-" + config.getValue()
                                + " - post 执行完毕。该filter执行链总用时(毫秒)：" + stopWatch.getLastTaskTimeMillis());
                    })
            );
        };
    }
}
