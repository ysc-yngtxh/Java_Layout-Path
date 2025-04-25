package com.example.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.GsonTool;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    public ReturnT<String> myJobHandler() {
        // 获取参数【参数可以是单参数，也可以是多参数，更可以是Json字符串】
        String jobParam = XxlJobHelper.getJobParam();
        log.info("接收調度中心参数...[{}]", jobParam);
        System.out.println("定时任务执行--" + LocalDateTime.now());

        // 控制台输出日志
        log.info("myXxlJobHandler execute...");
        try {
            // 书写业务逻辑
            //TODO

            // 写日志到调度中心日志中
            XxlJobHelper.log("myXxlJobHandler execute Success...");
            // 设置任务结果
            XxlJobHelper.handleSuccess();
        } catch (Exception e) {
            log.error("myXxlJobHandler execute Fail ...", e);

            // 写日志到调度中心日志中
            XxlJobHelper.log("myXxlJobHandler execute Fail...");
            // 设置任务结果
            XxlJobHelper.handleFail();
        }
        return ReturnT.SUCCESS;
    }



    /**
     * 2、分片广播任务
     */
    @XxlJob("shardingJobHandler")
    public void shardingJobHandler() throws Exception {

        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        XxlJobHelper.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardIndex, shardTotal);

        // 业务逻辑
        for (int i = 0; i < shardTotal; i++) {
            if (i == shardIndex) {
                XxlJobHelper.log("第 {} 片, 命中分片开始处理", i);
            } else {
                XxlJobHelper.log("第 {} 片, 忽略", i);
            }
        }

    }


    /**
     * 3、命令行任务
     *
     *  参数示例："ls -a" 或者 "pwd"
     */
    @XxlJob("commandJobHandler")
    public void commandJobHandler() throws Exception {
        String command = XxlJobHelper.getJobParam();
        int exitValue = -1;

        BufferedReader bufferedReader = null;
        try {
            // valid
            if (command==null || command.trim().length()==0) {
                XxlJobHelper.handleFail("command empty.");
                return;
            }

            // command split
            String[] commandArray = command.split(" ");

            // command process
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(commandArray);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            //Process process = Runtime.getRuntime().exec(command);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(process.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            // command log
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                XxlJobHelper.log(line);
            }

            // command exit
            process.waitFor();
            exitValue = process.exitValue();
        } catch (Exception e) {
            XxlJobHelper.log(e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        if (exitValue == 0) {
            // default success
        } else {
            XxlJobHelper.handleFail("command exit value("+exitValue+") is failed");
        }

    }


    /**
     * 4、跨平台Http任务
     *
     *  参数示例：
     *  <pre>
     *      {
     *          "url": "http://www.baidu.com",
     *          "method": "get",
     *          "data": "hello world"
     *      }
     *  </pre>
     */
    @XxlJob("httpJobHandler")
    public void httpJobHandler() throws Exception {

        // param
        String param = XxlJobHelper.getJobParam();
        if (param==null || param.trim().length()==0) {
            XxlJobHelper.log("param["+ param +"] invalid.");

            XxlJobHelper.handleFail();
            return;
        }

        // param parse
        String url;
        String method;
        String data;
        try {
            Map<String, String> paramMap = GsonTool.fromJson(param, Map.class);
            url = paramMap.get("url");
            method = paramMap.get("method");
            data = paramMap.get("data");
        } catch (Exception e) {
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail();
            return;
        }

        // param valid
        if (url==null || url.trim().length()==0) {
            XxlJobHelper.log("url["+ url +"] invalid.");

            XxlJobHelper.handleFail();
            return;
        }
        if (method==null || !Arrays.asList("GET", "POST").contains(method.toUpperCase())) {
            XxlJobHelper.log("method["+ method +"] invalid.");

            XxlJobHelper.handleFail();
            return;
        }
        method = method.toUpperCase();
        boolean isPostMethod = method.equals("POST");

        // request
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            // connection
            URL realUrl = new URL(url);
            connection = (HttpURLConnection) realUrl.openConnection();

            // connection setting
            connection.setRequestMethod(method);
            connection.setDoOutput(isPostMethod);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(3 * 1000);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");

            // do connection
            connection.connect();

            // data
            if (isPostMethod && data!=null && data.trim().length()>0) {
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.write(data.getBytes("UTF-8"));
                dataOutputStream.flush();
                dataOutputStream.close();
            }

            // valid StatusCode
            int statusCode = connection.getResponseCode();
            if (statusCode != 200) {
                throw new RuntimeException("Http Request StatusCode(" + statusCode + ") Invalid.");
            }

            // result
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            String responseMsg = result.toString();

            XxlJobHelper.log(responseMsg);

            return;
        } catch (Exception e) {
            XxlJobHelper.log(e);

            XxlJobHelper.handleFail();
            return;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e2) {
                XxlJobHelper.log(e2);
            }
        }

    }

    /**
     * 5、生命周期任务示例：任务初始化与销毁时，支持自定义相关逻辑；
     */
    @XxlJob(value = "demoJobHandler2", init = "init", destroy = "destroy")
    public void demoJobHandler2() {
        XxlJobHelper.log("XXL-JOB, Hello World.");
    }
    public void init() {
        log.info("init");
    }
    public void destroy() {
        log.info("destroy");
    }
}
