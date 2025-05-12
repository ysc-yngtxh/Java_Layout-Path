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
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 17:00
 * @apiNote TODO 自定义熔断降级异常处理(返回响应流)
 */
public class CustomBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        httpServletResponse.setStatus(429);

        PrintWriter out = httpServletResponse.getWriter();
        String msg = "Blocked by Sentinel - ";

        if (e instanceof FlowException) {
            msg += "Flow Exception";
        } else if (e instanceof DegradeException) {
            msg += "Degrade Exception";
        } else if (e instanceof SystemBlockException) {
            msg += "System Block Exception";
        } else if (e instanceof ParamFlowException) {
            msg += "ParamFlow Exception";
        } else if (e instanceof AuthorityException) {
            msg += "Authority Exception";
            httpServletResponse.setStatus(401);
        }

        out.print(msg);
        out.flush();
        out.close();
    }
}
