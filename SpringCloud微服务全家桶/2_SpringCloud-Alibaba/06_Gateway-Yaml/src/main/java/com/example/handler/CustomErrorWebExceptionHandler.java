package com.example.handler;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 23:41
 * @apiNote TODO
 */
@Component
@Order(-1)
public class CustomErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    // AbstractErrorWebExceptionHandler是一个抽象类，用于自定义异常的处理方式。
    // 它是Spring Webflux模块中的异常处理器，用于处理在Web请求处理过程中产生的异常。
    // 通过继承该类并实现其抽象方法，可以自定义异常处理的行为，例如定义错误响应的格式、错误码等。
    public CustomErrorWebExceptionHandler(ErrorAttributes errorAttributes
                                        , ApplicationContext applicationContext
                                        , ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request){
        // 获取异常信息
        Map<String, Object> map = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        // 构建响应
        return ServerResponse.status(HttpStatus.NOT_FOUND)  // 404状态码
                .contentType(MediaType.APPLICATION_JSON)    // 以 JSON 格式显示响应
                .body(BodyInserters.fromValue(map));        // 响应体(响应内容)
    }
}
