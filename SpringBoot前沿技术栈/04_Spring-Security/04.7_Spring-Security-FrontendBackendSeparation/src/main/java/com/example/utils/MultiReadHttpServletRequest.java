package com.example.utils;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author example
 * @dateTime 2023-05-03 17:32
 * @apiNote TODO 多次读取请求流 解决流只能读一次问题
 */
@Slf4j
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    public final byte[] body;

    /**
     * 构造一个包装给定请求的请求对象
     *
     * @param request 包装请求
     * @throws IllegalArgumentException if the request is null
     */
    public MultiReadHttpServletRequest(HttpServletRequest request) {
        // 通过构造方法将请求体传给父类
        super(request);
        this.body = getBodyString(request).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader( getInputStream() ));
    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    /**
     * 获取请求Body
     * @param request
     * @return String
     */
    public String getBodyString(ServletRequest request) {
        InputStream inputStream = null;
        BufferedReader bf = null;
        StringBuilder str = new StringBuilder();
        try {
            inputStream = request.getInputStream();
            bf = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = null;
            while ((line = bf.readLine()) != null){
                str.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str.toString();
    }

    /**
     * 将前端请求的表单数据转换成json字符串 - 前后端一体的情况下使用
     * @param request
     * @return String
     */
    public String getFormToJsonString(ServletRequest request) {
        Map<String, String> map = new HashMap<>();
        try {
            Enumeration<String> parameterNames = request.getParameterNames();
            String nextElement = null;
            while (parameterNames.hasMoreElements()) {
                nextElement = parameterNames.nextElement();
                map.put(nextElement, request.getParameter(nextElement));
            }
        } catch (Exception e) {
            log.debug("请求参数转换错误!",e);
        }
        return JSONObject.toJSONString(map);
    }

    public String getJsonToJsonStr(ServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            String str = null;
            while ((str = reader.readLine()) != null){
                stringBuilder.append(str);
            }

        } catch (IOException e) {
            log.debug("请求参数转换错误!",e);
        }
        return stringBuilder.toString();
    }
}
