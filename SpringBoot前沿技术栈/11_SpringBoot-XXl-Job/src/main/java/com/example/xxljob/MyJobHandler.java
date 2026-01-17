package com.example.xxljob;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.http.HttpTool;
import com.xxl.tool.http.http.HttpResponse;
import com.xxl.tool.http.http.enums.ContentType;
import com.xxl.tool.http.http.enums.Method;
import com.xxl.tool.json.GsonTool;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2023/2/26 15:20
 */
@Component
public class MyJobHandler {
    private static final Logger log = LoggerFactory.getLogger(MyJobHandler.class);

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob(value = "myJobHandler")
    public void myJobHandler() {
        // 获取参数【参数可以是单参数，也可以是多参数，更可以是Json字符串】
        String jobParam = XxlJobHelper.getJobParam();
        log.info("接收調度中心参数...[{}]", jobParam);
        System.out.println("定时任务执行--" + LocalDateTime.now());

        // 控制台输出日志
        log.info("myXxlJobHandler execute...");
        try {
            // TODO 书写业务逻辑...

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
        // default success
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
     * <p>
     * 参数示例："ls -a" 或者 "pwd"
     */
    @XxlJob("commandJobHandler")
    public void commandJobHandler() throws Exception {
        String command = XxlJobHelper.getJobParam();
        int exitValue = -1;

        BufferedReader bufferedReader = null;
        try {
            // valid
            if (command == null || command.trim().isEmpty()) {
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
            // Process process = Runtime.getRuntime().exec(command);

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
            XxlJobHelper.handleFail("command exit value(" + exitValue + ") is failed");
        }

    }


    /**
     * 4、跨平台Http任务
     * <p>
     * 参数示例：
     * <pre>
     *      {
     *          "url": "http://www.baidu.com",
     *          "method": "get",
     *          "data": "hello world"
     *      }
     *
     *      // 2、完整参数示例：
     *      {
     *          "url": "http://www.baidu.com",
     *          "method": "POST",
     *          "contentType": "application/json",
     *          "headers": {
     *              "header01": "value01"
     *          },
     *          "cookies": {
     *              "cookie01": "value01"
     *          },
     *          "timeout": 3000,
     *          "data": "request body data",
     *          "form": {
     *              "key01": "value01"
     *          },
     *          "auth": "auth data"
     *      }
     *  </pre>
     */
    @XxlJob("httpJobHandler")
    public void httpJobHandler() throws Exception {

        // param data
        String param = XxlJobHelper.getJobParam();
        if (param == null || param.trim().isEmpty()) {
            XxlJobHelper.log("param[" + param + "] invalid.");

            XxlJobHelper.handleFail();
            return;
        }

        // param parse
        HttpJobParam httpJobParam = null;
        try {
            httpJobParam = GsonTool.fromJson(param, HttpJobParam.class);
        } catch (Exception e) {
            XxlJobHelper.log(new RuntimeException("HttpJobParam parse error", e));
            XxlJobHelper.handleFail();
            return;
        }

        // param valid
        if (httpJobParam == null) {
            XxlJobHelper.log("param parse fail.");
            XxlJobHelper.handleFail();
            return;
        }
        if (StringTool.isBlank(httpJobParam.getUrl())) {
            XxlJobHelper.log("url[" + httpJobParam.getUrl() + "] invalid.");
            XxlJobHelper.handleFail();
            return;
        }
        if (!isValidDomain(httpJobParam.getUrl())) {
            XxlJobHelper.log("url[" + httpJobParam.getUrl() + "] not allowed.");
            XxlJobHelper.handleFail();
            return;
        }
        Method method = Method.POST;
        if (StringTool.isNotBlank(httpJobParam.getMethod())) {
            Method methodParam = Method.valueOf(httpJobParam.getMethod().toUpperCase());
            if (methodParam == null) {
                XxlJobHelper.log("method[" + httpJobParam.getMethod() + "] invalid.");
                XxlJobHelper.handleFail();
                return;
            }
            method = methodParam;
        }
        ContentType contentType = ContentType.JSON;
        if (StringTool.isNotBlank(httpJobParam.getContentType())) {
            for (ContentType contentTypeParam : ContentType.values()) {
                if (contentTypeParam.getValue().equals(httpJobParam.getContentType())) {
                    contentType = contentTypeParam;
                    break;
                }
            }
        }
        if (httpJobParam.getTimeout() <= 0) {
            httpJobParam.setTimeout(3000);
        }

        // do request
        try {
            HttpResponse httpResponse = HttpTool.createRequest()
                    .url(httpJobParam.getUrl())
                    .method(method)
                    .contentType(contentType)
                    .header(httpJobParam.getHeaders())
                    .cookie(httpJobParam.getCookies())
                    .body(httpJobParam.getData())
                    .form(httpJobParam.getForm())
                    .auth(httpJobParam.getAuth())
                    .execute();

            XxlJobHelper.log("StatusCode: " + httpResponse.statusCode());
            XxlJobHelper.log("Response: <br>" + httpResponse.response());
        } catch (Exception e) {
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail();
        }
    }

    /**
     * domain white-list, for httpJobHandler
     */
    private static Set<String> DOMAIN_WHITE_LIST = Set.of(
            "http://www.baidu.com",
            "http://cn.bing.com"
    );

    /**
     * valid if domain is in white-list
     */
    private boolean isValidDomain(String url) {
        if (url == null || DOMAIN_WHITE_LIST.isEmpty()) {
            return false;
        }
        for (String prefix : DOMAIN_WHITE_LIST) {
            if (url.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /*public static void main(String[] args) {
        HttpJobParam httpJobParam = new HttpJobParam();
        httpJobParam.setUrl("http://www.baidu.com");
        httpJobParam.setMethod(Method.POST.name());
        httpJobParam.setContentType(ContentType.JSON.getValue());
        httpJobParam.setHeaders(Map.of("header01", "value01"));
        httpJobParam.setCookies(Map.of("cookie01", "value01"));
        httpJobParam.setTimeout(3000);
        httpJobParam.setData("request body data");
        httpJobParam.setForm(Map.of("form01", "value01"));
        httpJobParam.setAuth("auth data");

        logger.info(GsonTool.toJson(httpJobParam));
    }*/

    /**
     * http job param
     */
    @Data
    private static class HttpJobParam {
        private String url;                                     // 请求 Url
        private String method;                                  // Method
        private String contentType;                             // Content-Type
        private Map<String, String> headers;                    // 存储请求头
        private Map<String, String> cookies;                    // Cookie（需要格式转换）
        private int timeout;                                    // 请求超时时间
        private String data;                                    // 存储请求体
        private Map<String, String> form;                       // 存储表单数据
        private String auth;                                    // 鉴权信息
    }

    /**
     * 5、生命周期任务示例：任务初始化与销毁时，支持自定义相关逻辑；
     */
    @XxlJob(value = "demoJobHandler2", init = "init", destroy = "destroy")
    public void demoJobHandler2() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
    }

    public void init() {
        log.info("init");
    }

    public void destroy() {
        log.info("destroy");
    }
}
