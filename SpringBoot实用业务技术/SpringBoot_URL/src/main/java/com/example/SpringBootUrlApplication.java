package com.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUrlApplication.class, args);
    }

    // URL定义
    // 统一资源标识符（Uniform Resource Identifier ，URL）是采用一种特定语法标识一个资源的字符串。
    // 所标识的资源可能是服务器上的一个文件。Java的URL网络类可以让你通过URL去练级网络服务器并获取资源。
    //
    // URL的格式如下：
    //    protocol://host:port/path?query#fragment
    //    protocol（协议）可以是HTTP，HTTPS，FTP和File，port为端口号，path为文件路径及文件名。
    //    例如：https://www.baidu.com/
    @Test
    public void contextLoads() throws IOException, URISyntaxException {
        URL url1 = new URL("http://localhost:8080");
        URL url2 = new URL("http", "localhost", 8080, "/");
        // 获取此URL的内容
        Object content = url1.getContent();
        System.out.println("URL的内容" + content);
        // 获取此 URL的文件名
        String file = url1.getFile();
        System.out.println("URL的文件名" + file);
        // 返回相当于此URL的URI
        URI uri = url1.toURI();
        System.out.println("此URL的URI" + uri);
    }

}
