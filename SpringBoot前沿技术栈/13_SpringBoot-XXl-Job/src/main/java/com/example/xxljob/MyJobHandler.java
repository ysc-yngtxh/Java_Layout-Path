package com.example.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 15:19
 */
@Component
public class MyJobHandler {
    private static final Logger log = LoggerFactory.getLogger(MyJobHandler.class);

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob(value = "myJobHandler")
    public ReturnT<String> myJobHandler(String param) throws Exception{
        XxlJobHelper.log("XXL-JOB, Hello World.");
        log.info("定时任务开始执行。。。。。。");
        System.out.println("定时任务执行--" + LocalDateTime.now());
        return ReturnT.SUCCESS;
    }
}
