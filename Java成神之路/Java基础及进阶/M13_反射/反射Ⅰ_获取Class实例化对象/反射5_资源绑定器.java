package M13_反射.反射Ⅰ_获取Class实例化对象;

import java.util.ResourceBundle;

/*
  java.util包下提供了一个资源绑定器，便于获取属性配置文件中的内容。
  使用以下这种方式的时候，属性配置文件xxx.properties必须放到类路径下。
  有了资源绑定器，就不需要再new流对象，不需要new Properties。
 */
public class 反射5_资源绑定器 {

    public static void main(String[] args) {
        // 资源绑定器，只能绑定 xxx.properties 文件。并且这个文件必须在类路径下
        // 并且在写路径的时候，路径后面的扩展名不能写
        ResourceBundle bundle = ResourceBundle.getBundle("classInfo");

        String className = bundle.getString("className1");
        System.out.println(className);
    }
}