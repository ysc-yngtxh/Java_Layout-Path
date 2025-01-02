package K11_流.流Ⅵ_对象专属流;

import java.io.FileReader;
import java.util.Properties;

/*
  IO + Properties(继承于HashTable) 的联合应用
  非常好的一个设计理念：
        以后经常改变的数据，可以单独写到一个文件中，使用程序动态读取。
        将来只需要修改这个文件的内容，Java代码不需要改动，不需要重新编译，服务器也不需要重启，就可以拿到动态信息。

        类似于以上机制的这种文件被称为配置文件
        并且当配置文件中的内容格式是：key=value 的时候，我们把这种配置文件叫做属性配置文件

        Java规范中有要求：属性配置文件建议以.properties结尾，但这不是必须的
 */
public class 流17_IO和Properties联合使用 {
    public static void main(String[] args) throws Exception{
        /*
          Properties是一个Map集合，key和value都是String类型
          想将userinfo文件中的数据加载到Properties对象当中
         */
        // 新建一个输入字符流对象
        FileReader fr = new FileReader(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/流Ⅵ_对象专属流/userinfo.properties");

        // 新建一个Map集合
        Properties pro = new Properties();

        // 调用Properties对象的Load方法将文件中的数据加载到Map集合中
        // 文件中的数据顺着管道加载到Map集合当中，其中 (等号=) 左边做key，右边做value
        pro.load(fr);

        // 通过key来获取value
        String username = pro.getProperty("username");
        System.out.println(username);

        String password = pro.getProperty("password");
        System.out.println(password);
    }
}