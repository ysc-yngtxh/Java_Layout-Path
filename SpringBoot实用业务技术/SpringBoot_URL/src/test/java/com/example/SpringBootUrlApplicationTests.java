package com.example;

import com.example.utils.ChineseToUrl;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @SpringBootTest
class SpringBootUrlApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(SpringBootUrlApplicationTests.class);

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
        URL url1 = new URL("https://devapi.qweather.com/v7/weather/now?location=101110101&key=f83cf5800baf480fa4c3bc6a474ffd90");
        URL url2 = new URL("http", "localhost", 8080, "/");
        URL url3 = new URL("file:" + ABSOLUTE_PATH + "/src/main/resources/application.properties");

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
       System.out.print("\n");

        // URL只能解析ASCII字符(256个字符)，不能解析中文及特殊字符，因此需要将请求路径中的中文或者特殊字符进行编码处理
        String strPath = "https://geoapi.qweather.com/v2/city/lookup?location=西安&key=f83cf5800baf480fa4c3bc6a474ffd90";
        System.out.println("请求路径携带中文部分开始断开，表示无法解析：" + new URL(strPath).toURI());
       System.out.print("\n");

        // 使用工具类将中文或者特殊字符解析为 ASCII码
        String urls = ChineseToUrl.ChineseToUrls(strPath);
        System.out.println("使用工具类将请求路径中带中文的解析为：" + urls);
        System.out.println("路径编码之后，路径没有从中间断开，并可以直接访问：" + new URL(urls));
       System.out.print("\n");

        // 将字转为码是编码
        System.out.println("使用URLEncoder编码：" + URLEncoder.encode("西安", StandardCharsets.UTF_8));
        // 注意：不要将整个URL请求路径进行编码。否则会将路径中‘://’、‘=’、‘&’也进行编码。URLEncoder只适合做URL的局部编码
        System.out.println("无法将 URL 中所需要的部分进行保留：" + URLEncoder.encode(strPath, StandardCharsets.UTF_8));
       System.out.print("\n");


        // 将码转为字是解码
        System.out.println("使用URLDecoder解码：" + URLDecoder.decode("%E7%BD%97%E5%BE%B7", "utf-8"));
    }

    // TODO URLConnection类
    @Test
    public void contextLoads1() throws IOException {
        URL url1 = new URL("http://localhost:8081/treeChildrenSet");
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        // 设置连接通道的请求方法：GET、POST、PUT、DELETE等
        conn.setRequestMethod("GET");
        // 设置请求参数
        conn.setRequestProperty("contentType", "application/json;charset=utf-8");
        // 设置连接超时时间，单位毫秒
        conn.setConnectTimeout(360000);
        // 设置读取超时时间，单位毫秒
        conn.setReadTimeout(360000);

        if (conn.getResponseCode() == 200) {
            // 获取响应的头字段
            Map<String, List<String>> headers = conn.getHeaderFields();
            System.out.println(headers);
            // 获取响应的字节流
            InputStream inputStream = conn.getInputStream();
            System.out.println(inputStream);
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                System.err.println(str);
            }

            br.close();
            isr.close();
            conn.disconnect();
        }
    }


    // URLClassLoader是ClassLoader的实现类，既能从本地加载二进制文件类，也能从远程加载类。它有两个构造函数，即：
    //   URLClassLoader(URL[] urls)，使用默认的父类加载器（SystemClassLoader）创建一个ClassLoader对象
    //   URLClassLoader（URL[] urls, ClassLoader parent)，使用指定的类加载器作为父类加载器创建ClassLoader对象
    @Test
    public void contextLoads2() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        File file = new File(System.getProperty("user.dir") + "/src/main/java/com/example");
        URL url = file.toURI().toURL();
        ClassLoader loader = new URLClassLoader(new URL[]{url});
        // loadClass()参数：所需class的含包名的全名
        Class<?> clazz = loader.loadClass("com.example.Hello");
        System.out.println("当前类加载器：" + clazz.getClassLoader());
        System.out.println("父类加载器：" + clazz.getClassLoader().getParent());
        clazz.newInstance();
    }
}
