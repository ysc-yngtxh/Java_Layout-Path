package com.example.utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author example
 * @dateTime 2023-05-04 12:13
 * @apiNote TODO 多次请求响应体
 */
public class MultiReadHttpServletResponse extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final HttpServletResponse response;

    public MultiReadHttpServletResponse(HttpServletResponse response) {
        // 通过构造方法将响应体传给父类
        super(response);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        this.response = response;
    }

    public byte[] getBody() {
        /**
         * 这里可能比较难懂，你会发现你这里返回的只是包装的缓冲流转成字节数据，实际是没有数据的，那调用该方法做输出还是有数据，
         * 那数据从何而来呢？事实上在 getOutputStream() 方法的返回值ServletOutputStream对象中重写的write()方法,
         * Servlet会处理将springMvc返回的值放入到 OutputStream 类中，而缓冲流 ByteArrayOutputStream 是继承其 OutputStream 的 ，
         * 故而，这里的 byteArrayOutputStream.write(b) 已然是将返回数据存入到缓冲流 ByteArrayOutputStream 中*/
        return byteArrayOutputStream.toByteArray();
    }


    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(getOutputStream(), this.response.getCharacterEncoding()));
    }


    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }

            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }

            @Override
            public void flush() throws IOException {
                if (!response.isCommitted()) {
                    byte[] body = byteArrayOutputStream.toByteArray();
                    ServletOutputStream outputStream = response.getOutputStream();
                    outputStream.write(body);
                    outputStream.flush();
                }
            }
        };
    }
}
