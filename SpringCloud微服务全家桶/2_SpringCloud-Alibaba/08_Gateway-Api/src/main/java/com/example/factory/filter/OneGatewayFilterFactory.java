package com.example.factory.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Mono;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 14:30
 * @apiNote TODO
 */
@Component
@Slf4j
public class OneGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    // 所有“预”过滤器逻辑都会被执行。然后发出代理请求。在发出代理请求之后，将运行“post”过滤器逻辑。
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            // pre-filter “预”过滤器逻辑
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            log.info(config.getName() + "-" + config.getValue() + " - pre 开始执行的时间：" + System.currentTimeMillis());
            return chain.filter(exchange).then(
                    // 在执行完代理请求之后，再运行这时候的“post”过滤器逻辑。
                    // post-filter
                    Mono.fromRunnable(() -> { // 创建一个Mono对象，它表示一个延迟执行的任务。
                        stopWatch.stop();
                        log.info(config.getName() + "-" + config.getValue() + " - post 执行时间(毫秒):" + System.currentTimeMillis());
                        log.info(config.getName() + "-" + config.getValue() + " - post 执行完毕。该filter执行链总用时(毫秒)：" + stopWatch.getLastTaskTimeMillis());
                    })
            );
        };
    }
}
