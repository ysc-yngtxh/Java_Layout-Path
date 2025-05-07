package com.example;

import com.example.load.CustomClassLoader;
import com.example.utils.ChineseToUrl;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
    public void contextLoads() throws IOException, URISyntaxException {
        // 因为所携带路径中存在中文，因此辨识路径便会从中文部分断开
        URL url1 = new URL("https://devapi.qweather.com/v7/weather/now?location=101110101&key=f83cf5800baf480fa4c3bc6a474ffd90");
        URL url2 = new URL("http", "localhost", 8080, "/");
        URL url3 = new URL("file:" + ABSOLUTE_PATH + "/src/main/resources/application.properties");
        // 上述的构造器在 Jdk20版本中被弃用，可使用如下方式
        URL uri4 = new URI("https://example.com").toURL();
        URL uri5 = URI.create("https://example.com").toURL();

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
        System.out.println("使用URLDecoder解码：" + URLDecoder.decode("%E7%BD%97%E5%BE%B7", StandardCharsets.UTF_8));
    }

    // TODO URLConnection类
    @Test
    public void contextLoads1() throws IOException, URISyntaxException {
        // 创建URL对象
        URL url = new URI("http://ip-api.com/json").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置连接通道的请求方法：GET、POST、PUT、DELETE等
        conn.setRequestMethod("GET");
        // 设置请求参数
        conn.setRequestProperty("contentType", "application/json; charset=UTF-8");
        // 设置连接超时时间，单位毫秒
        conn.setConnectTimeout(360000);
        // 设置读取超时时间，单位毫秒
        conn.setReadTimeout(360000);

        if (conn.getResponseCode() == 200) {
            // 获取响应的头字段
            Map<String, List<String>> headers = conn.getHeaderFields();
            System.out.println("获取响应头信息：" + headers);
            // 获取响应的字节流
            InputStream inputStream = conn.getInputStream();
            System.out.println("获取响应字节流：" + inputStream);
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                System.err.println(str);
            }

            // 关闭流
            br.close();
            isr.close();
            conn.disconnect();
        }
    }


    // URLClassLoader是ClassLoader的实现类，既能从本地加载二进制文件类，也能从远程加载类。它有两个构造函数，即：
    //    URLClassLoader(URL[] urls)：
    //       创建一个 URLClassLoader 实例，这里没有显式地指定父类加载器，它将会使用系统的默认父类加载器（AppClassLoader/SystemClassLoader）。
    //       URLClassLoader 会首先尝试让其默认的父类加载器从应用程序的类路径中加载类。
    //       在 SpringBoot 项目中应用程序的类路径为（CLASSPATH：即 target/classes 目录）
    //       如果父类加载器在 CLASSPATH 中找到了这个类并成功加载，那么 URLClassLoader 就不需要再做任何事情。
    //       如果父类加载器在 CLASSPATH 中没有找到这个类，那么 URLClassLoader(即子加载器) 就会尝试从指定的 URL[] urls 路径中加载类。
    //    URLClassLoader（URL[] urls, ClassLoader parent)：
    //       创建一个 URLClassLoader 实例，显式地指定父类加载器 ClassLoader parent，父类加载器从其下的类路径中加载类。
    //       在SpringBoot项目中指定 应用类[AppClassLoader]/系统类[SystemClassLoader] 加载器会从类路径（CLASSPATH：即 target/classes 目录）中加载类。
    //       如果父类加载器在 CLASSPATH 中没有找到这个类，那么 URLClassLoader(即子加载器) 就会尝试从指定的 URL[] urls 路径中加载类。
    //       注意⚠️：当第二个传参数 ClassLoader parent 父类加载器为 null 时
    //       那么 URLClassLoader 将独立地尝试加载传参 URL[] urls 路径下的类，而不会委托给其他类加载器。
    @Test
    public void contextLoads2() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        URL url = new File(System.getProperty("user.dir")).toURI().toURL();
        // 三种写法：1️⃣隐式使用默认的应用类父加载器 2️⃣指定当前上下文的类加载器作为父加载器 3️⃣指定系统类加载器作为父加载器
        try (URLClassLoader loader1 = new URLClassLoader(new URL[]{url});
             URLClassLoader loader2 = new URLClassLoader(new URL[]{url}, this.getClass().getClassLoader());
             URLClassLoader loader3 = new URLClassLoader(new URL[]{url}, ClassLoader.getSystemClassLoader())
        ) {
            // 按理来说：URL 的地址跟加载的全限定名称路径并不能完美匹配上（缺少/target/classes）,并且累加载器只能加载 class 文件，不能加载 Java 文件。
            //         所以这里应该会报异常找不到指定的 Hello 类。但是执行结果是正常输出，说明使用了父加载器从默认路径 CLASSPATH 下加载类
            Class<?> clazz = loader1.loadClass("com.example.pojo.Hello");
            System.out.println("使用 URLClassLoader(URL[] urls)，当前类加载器：" + clazz.getClassLoader());
            System.out.println("使用 URLClassLoader(URL[] urls)，父类加载器：" + clazz.getClassLoader().getParent());
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod("sayHello");
            method.invoke(instance);
        } catch (Exception e) {
            log.error("URLClassLoader加载类失败", e);
        }


        // 第四种写法：指定父类加载器为 null，即不使用任何父类加载器，独立地尝试加载传参 URL[] urls 路径下的类
        URL url1 = new File(System.getProperty("user.dir") + "/target/classes").toURI().toURL();
        ClassLoader loaderAndNull = new URLClassLoader(new URL[]{url1}, null);
        Class<?> clazz3 = loaderAndNull.loadClass("com.example.pojo.Hello");
        System.out.println("使用 URLClassLoader(new URL[]{url}, null)，当前类加载器：" + clazz3.getClassLoader());
        System.out.println("使用 URLClassLoader(new URL[]{url}, null)，父类加载器：" + clazz3.getClassLoader().getParent());
        clazz3.getDeclaredConstructor().newInstance();


        // 第五种写法：自定义类加载器
        CustomClassLoader customClassLoader = new CustomClassLoader(url.getPath());
        Class<?> clazz2 = customClassLoader.loadClass("com.example.pojo.Hello");
        System.out.println("使用自定义的加载器后，当前类加载器：" + clazz2.getClassLoader());
        System.out.println("使用自定义的加载器后，父类加载器：" + clazz2.getClassLoader().getParent());
        clazz2.getDeclaredConstructor().newInstance();
    }
}
