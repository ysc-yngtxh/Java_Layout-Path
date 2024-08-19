package com.example;

import com.example.utils.ChineseToUrl;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

// @SpringBootTest
class SpringBootUrlApplicationTests {

    public static final String ABSOLUTE_PATH = System.getProperty("user.dir");

    // URL定义
    // 统一资源标识符（Uniform Resource Identifier ，URL）是采用一种特定语法标识一个资源的字符串。
    // 所标识的资源可能是服务器上的一个文件。Java的URL网络类可以让你通过URL去练级网络服务器并获取资源。
    //
    // URL的格式如下：
    //    protocol://host:port/path?query#fragment
    //    protocol（协议）可以是http://、https://、ftp://、file://，比如: http://就是超文本传输协议，
    //    port为端口号，path为文件路径及文件名。
    //    例如：https://www.baidu.com/
    @Test
    public void contextLoads() throws IOException, URISyntaxException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 因为所携带路径中存在中文，因此辨识路径便会从中文部分断开
        URL url0 = new URL("https://geoapi.qweather.com/v2/city/lookup?location=西安&key=f83cf5800baf480fa4c3bc6a474ffd90");
        String urls = ChineseToUrl.ChineseToUrls("https://geoapi.qweather.com/v2/city/lookup?location=西安&key=f83cf5800baf480fa4c3bc6a474ffd90");
        URL url = new URL(urls);
        URL url1 = new URL("https://devapi.qweather.com/v7/weather/now?location=101110101&key=f83cf5800baf480fa4c3bc6a474ffd90");
        URL url2 = new URL("http", "localhost", 8080, "/");
        URL url3 = new URL("file:" + ABSOLUTE_PATH + "/src/main/resources/application.properties");

        System.out.println("URL 为：" + url3.toString());
        System.out.println("协议为：" + url3.getProtocol());
        System.out.println("验证信息：" + url3.getAuthority());
        System.out.println("获取此URL的文件名及请求参数：" + url3.getFile());
        System.out.println("主机名：" + url3.getHost());
        System.out.println("路径：" + url3.getPath());
        System.out.println("端口：" + url3.getPort());
        System.out.println("默认端口：" + url3.getDefaultPort());
        System.out.println("请求参数：" + url3.getQuery());
        System.out.println("定位位置：" + url3.getRef());
        System.out.println("此URL的URI：" + url3.toURI());
        // 打开与此 URL ，并返回一个 InputStream ，以便从该连接读取。
        InputStream in = url3.openStream();
        new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines().forEach(System.out::println);

        // 将字转化为码是编码
        System.out.println(URLEncoder.encode("罗德", "UTF-8"));

        // 将码转为字是解码
        System.out.println(URLDecoder.decode("10101101011", "utf-8"));
    }

    @Test
    public void contextLoads1() throws IOException {
        URL url = new URL("https://devapi.qweather.com/v7/weather/now?location=101110101&key=f83cf5800baf480fa4c3bc6a474ffd90");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("content-type", "application/json;charset=utf-8");
        // 包装Basic信息
        String username = "elastic";
        String password = "Z*h-KkWj6FGdy2gYUQpC";
        String auth = username + ":" + password;  // elastic:Z*h-KkWj6FGdy2gYUQpC
        // 对其进行加密
        byte[] rel = Base64.encodeBase64(auth.getBytes(), true);
        // 注意不要使用数组工具类的toString方法：Arrays.toString(rel)
        String res = new String(rel);
        // 设置Basic认证
        conn.setRequestProperty("Authorization", "Basic " + res);
        // 允许输出流/允许参数
        conn.setDoOutput(true);
        // 获取输出流，就是获取请求路径的一个输出
        OutputStream out = conn.getOutputStream();
        String params = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"title\": \"小米\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        out.write(params.getBytes());  // 输出的请求中携带的数据

        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            while ((str = br.readLine()) != null) {
                System.err.println(str);
            }
            br.close();
        }
    }


    // URLClassLoader是ClassLoader的一个实现类，它既能从本地加载二进制文件类，也可以从远程加载类。
    // 它有两个构造函数， 即
    // URLClassLoader(URL[] urls)，使用默认的父类加载器（SystemClassLoader）创建一个ClassLoader对象
    // URLClassLoader（URL[] urls, ClassLoader parent)，使用指定的类加载器作为父类加载器创建ClassLoader对象
    @Test
    public void contextLoads2() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        File file = new File(System.getProperty("user.dir") + "/target/classes/com/example/");
        URL url = file.toURI().toURL();
        ClassLoader loader = new URLClassLoader(new URL[]{url});
        Class<?> clazz = loader.loadClass("Hello");
        System.out.println("当前类加载器" + clazz.getClassLoader());
        System.out.println("父类加载器" + clazz.getClassLoader().getParent());
        clazz.newInstance();
    }
}
