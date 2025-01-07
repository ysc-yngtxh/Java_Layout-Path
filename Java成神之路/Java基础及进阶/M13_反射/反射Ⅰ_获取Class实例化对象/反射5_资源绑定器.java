package M13_反射.反射Ⅰ_获取Class实例化对象;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/*
  java.util包下提供了一个资源绑定器，便于获取属性配置文件中的内容。
  使用以下这种方式的时候，属性配置文件xxx.properties必须放到类路径下。
  有了资源绑定器，就不需要再new流对象，不需要new Properties。
 */
public class 反射5_资源绑定器 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 资源绑定器，只能绑定 xxx.properties 文件。并且这个文件必须在类路径下
        // 并且在写路径的时候，路径后面的扩展名不能写
        ResourceBundle bundle1 = ResourceBundle.getBundle("classInfo");

        String className1 = bundle1.getString("className1");
        System.out.println(className1);
        // 中文乱码：可以将 ISO-8859-1 编码的字符串转换为 UTF-8
        System.out.println(
                new String(className1.getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8)
        );


        // 如果需要读取其他的属性配置文件，需要使用带有Locale参数的方法。拼接的文件名：xxx_en_US.properties
        ResourceBundle bundle3 = ResourceBundle.getBundle("classInfo", Locale.US);

        String className3 = bundle3.getString("className1");
        System.out.println(className3);
    }
}