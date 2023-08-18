package L12反射.B2获取Class实例化对象;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

public class 获取类路径下文件的绝对路径3 {
    public static void main(String[] args) throws IOException, URISyntaxException {

        //注意：试用一下通用方式的前提是：这个文件必须在类路径下。我调的是src为类路径

        /*
          解释：
              Thread.currentThread()  当前线程对象
              getContextClassLoader() 是线程对象的方法，可以获取当前线程的类加载器对象
              getResource()          【获取资源】这是类加载器对象的方法，当前线程的类加载器默认从类的根路径下加载资源
              toURI()                 解决中文乱码问题
              getPath()               该方法返回此抽象路径名的路径名字符串的形式。

         */
        String path = Thread.currentThread().getContextClassLoader().getResource("classinfo.properties").toURI().getPath();
        System.out.println(path);

        String paths = Thread.currentThread().getContextClassLoader().getResource("classinfo.properties").getPath();
        System.out.println( URLDecoder.decode(paths, "UTF-8") );//解决中文乱码问题

        // 获取当前项目路径
        System.err.println("path1: " + System.getProperty("user.dir"));

        // 通过 File 获取当前项目路径
        File directory = new File(""); // 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println("path2: " + courseFile);
    }
}
