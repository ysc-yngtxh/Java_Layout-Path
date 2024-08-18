package com.example;

import com.example.utils.ChineseToUrl;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xmlunit.builder.Input;

@SpringBootApplication
public class SpringBootUrlApplication {

    public static final String ABSOLUTE_PATH = System.getProperty("user.dir");

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
    public void contextLoads() throws IOException, URISyntaxException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 因为所携带路径中存在中文，因此辨识路径便会从中文部分断开
        URL url0 = new URL("https://geoapi.qweather.com/v2/city/lookup?location=西安&key=f83cf5800baf480fa4c3bc6a474ffd90");
        String urls = ChineseToUrl.ChineseToUrls("https://geoapi.qweather.com/v2/city/lookup?location=西安&key=f83cf5800baf480fa4c3bc6a474ffd90");
        URL url = new URL(urls);
        URL url1 = new URL("https://devapi.qweather.com/v7/weather/now?location=101110101&key=f83cf5800baf480fa4c3bc6a474ffd90");
        URL url2 = new URL("http", "localhost", 8080, "/");
        URL url3 = new URL("file:" + ABSOLUTE_PATH + "/src/main/resources/application.properties");

        // URL 类相关方法
        // url(url0);
        url(url);

        // URLConnection 用法
        urlConnection(url2);

        // URLClassLoader是ClassLoader的一个实现类，它既能从本地加载二进制文件类，也可以从远程加载类。
        // 它有两个构造函数， 即
        // URLClassLoader(URL[] urls)，使用默认的父类加载器（SystemClassLoader）创建一个ClassLoader对象
        // URLClassLoader（URL[] urls, ClassLoader parent)，使用指定的类加载器作为父类加载器创建ClassLoader对象
        urlClassLoader();

        // 将字转化为码是编码
        System.out.println(URLEncoder.encode("罗德", "UTF-8"));

        // 将码转为字是解码
        System.out.println(URLDecoder.decode("10101101011", "utf-8"));
    }

    // TODO URL
    private static void url(URL urlName) throws IOException, URISyntaxException {
        System.out.println("URL 为：" + urlName.toString());
        System.out.println("协议为：" + urlName.getProtocol());
        System.out.println("验证信息：" + urlName.getAuthority());
        System.out.println("获取此URL的文件名及请求参数：" + urlName.getFile());
        System.out.println("主机名：" + urlName.getHost());
        System.out.println("路径：" + urlName.getPath());
        System.out.println("端口：" + urlName.getPort());
        System.out.println("默认端口：" + urlName.getDefaultPort());
        System.out.println("请求参数：" + urlName.getQuery());
        System.out.println("定位位置：" + urlName.getRef());
        System.out.println("此URL的URI：" + urlName.toURI());
        // 打开与此 URL ，并返回一个 InputStream ，以便从该连接读取。
        InputStream in = urlName.openStream();
        new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines().forEach(System.out::println);
    }

    // TODO URLConnection
    private static void urlConnection(URL url2) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
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


    // TODO UrlClassLoader
    private Connection urlClassLoader() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = null;
        // 创建URL数组
        URL[] urls = {new URL("file:" + ABSOLUTE_PATH + "/src/main/resources/lib/mysql-connector-java-8.0.25.jar")};
        // 以默认的ClassLoader作为父类的ClassLoader, 创建URLClassLoader
        URLClassLoader myClassLoader = new URLClassLoader(urls);
        System.out.println("默认父类加载器： " + myClassLoader.getParent());
        System.out.println("默认父类加载器路径： " + myClassLoader.getParent().getResource(""));
        System.out.println("当前的类加载器路径： " + myClassLoader.getResource(""));

        // 加载 mysql-connector-java-8.0.25.jar 包里面的\com\mysql\jdbc\Driver.class 驱动，并创建实例
        // 加载路径 myClassLoader.getResource("")
        Driver driver = (Driver) myClassLoader.loadClass("com.mysql.cj.jdbc.Driver").newInstance();
        // 创建一个设置jdbc连接属性的Properties对象
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "131474");
        // 调用driver的connection来取得连接
        conn = driver.connect("jdbc:mysql://localhost:3306/mysql", props);

        return conn;
    }
}
