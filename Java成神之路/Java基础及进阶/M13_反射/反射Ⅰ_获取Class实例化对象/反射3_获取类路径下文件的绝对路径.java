package M13_反射.反射Ⅰ_获取Class实例化对象;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

public class 反射3_获取类路径下文件的绝对路径 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // 注意：试用一下通用方式的前提是：这个文件必须在类路径下。我调的是src为类路径

        /*
           Thread.currentThread()  当前线程对象
           getContextClassLoader() 是线程对象的方法，可以获取当前线程的类加载器对象
           getResource()          【获取资源】这是类加载器对象的方法，当前线程的类加载器默认从类的根路径下加载资源(.class文件)
           toURI()                 解决中文乱码问题
           getPath()               该方法返回此抽象路径名的路径名字符串的形式。
         */
        String path = Thread.currentThread()
                .getContextClassLoader()
                .getResource("classInfo.properties")
                .toURI()
                .getPath();
        System.out.println(path);

        String paths = Thread.currentThread()
                .getContextClassLoader()
                .getResource("classInfo.properties")
                .getPath();
        System.out.println( URLDecoder.decode(paths, "UTF-8") ); // 解决中文乱码问题

        String paths1 = Thread.currentThread()
                .getContextClassLoader()
                .getResource("")
                .getPath();
        System.out.println( URLDecoder.decode(paths1, "UTF-8") ); // 解决中文乱码问题

        // 获取当前工程项目路径
        System.err.println("path1: " + System.getProperty("user.dir"));

        // 通过 File 获取当前工程项目路径
        File directory = new File(""); // 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println("path2: " + courseFile);

        // 获取当前工程下的子模块项目路径
        String paths2 = 反射3_获取类路径下文件的绝对路径.class
                .getClassLoader()
                .getResource("")
                .toURI()
                .getPath();
        // 这里没有模块，因此只能获取到工程项目路径下的 ../out/production/Java成神之路/
        System.out.println(paths2);
        // 这里没有模块项目，所以没有target目录，因此会报错
        String substring = paths2.substring(0, paths2.indexOf("/target"));
        System.out.println(substring);
    }
}
