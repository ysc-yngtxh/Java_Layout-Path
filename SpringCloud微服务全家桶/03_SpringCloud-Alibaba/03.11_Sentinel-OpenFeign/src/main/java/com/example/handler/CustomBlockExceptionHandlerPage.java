package com.example.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-11 10:30
 * @apiNote TODO 自定义熔断降级异常处理(跳转到页面)
 */
public class CustomBlockExceptionHandlerPage implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        String msg = "";

        if (e instanceof FlowException) {
            msg = "page.html";
        } else if (e instanceof DegradeException) {
            msg = "/resourcePage";
        } else if (e instanceof SystemBlockException) {
            // 实际服务没有定义/**路径，工作业务自己补充
            msg = "/**";
        } else if (e instanceof ParamFlowException) {
            msg = "/**";
        } else if (e instanceof AuthorityException) {
            msg = "/**";
        }

        // 请求转发
        httpServletRequest.getRequestDispatcher(msg)
                .forward(httpServletRequest, httpServletResponse);
    }
}
